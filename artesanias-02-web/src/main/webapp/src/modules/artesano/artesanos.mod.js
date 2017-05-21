(function (ng) {
  var mod = ng.module('artesanoModule', ['ui.router']);

  mod.constant('artesanosContext', 'api/artesanos');
  mod.constant('ciudadesContext', 'api/ciudades');
  mod.constant('artesaniasContext', '/artesanias');
  mod.constant('reviewsContext', '/reviews');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/artesano/';

      $urlRouterProvider.otherwise('/artesanos/list');

      $stateProvider
        .state('artesanos', {
          url: '/artesanos',
          abstract: true,
          resolve: {
            artesanos: ['$http', 'artesanosContext',
              function ($http, artesanosContext) {
                return $http.get(artesanosContext);
              }]
          },
          views: {
            'mainView': {
              templateUrl: basePath + 'artesanos.html',
              controller: ['$scope', 'artesanos',
                function ($scope, artesanos) {
                  $scope.artesanosRecords = artesanos.data;
                }]
            }
          }
        })
        .state('artesanosList', {
          url: '/list',
          parent: 'artesanos',
          resolve: {
            ciudades: ['$http', 'ciudadesContext',
              function ($http, ciudadesContext) {
                return $http.get(ciudadesContext);
              }]
          },
          views: {
            'listView': {
              templateUrl: basePath + 'artesanos.list.html',
              controller: ['$scope', '$http', 'artesanosContext', 'ciudades', '$state',
                function ($scope, $http, artesanosContext, ciudades, $state) {

                  // Variables
                  $scope.isExtraDetailRow = ($scope.artesanosRecords.length % 6 ) !== 0;
                  $scope.numDetailsRows = Math.ceil($scope.artesanosRecords.length / 6);
                  $scope.detailsRows = [];
                  $scope.alerta = false;
                  $scope.removeEnabled = false;
                  $scope.artesanosToRemove = [];
                  $scope.squareArtesanos = [];
                  $scope.isDetailShown = true;

                  $scope.cities = ciudades.data;

                  // Init
                  initDetailsRows();
                  initRemovedArtesanos();

                  // Control Detail
                  $scope.isDetailRowShown = function (index) {
                    var i = Math.ceil(( index + 1 ) / 6);
                    return $scope.detailsRows[i - 1];
                  };
                  $scope.isLastRowDetail = function () {
                    return $scope.detailsRows[$scope.detailsRows.length - 1];
                  };

                  // Crear
                  $scope.crear = function () {
                    setDetails();
                    $scope.isDetailShown = false;
                    $state.go('artesanosList', {});

                    var name = document.getElementById('nombre').value;
                    var ide = document.getElementById('identificacion').value;
                    var tel = document.getElementById('telefono').value;

                    var nuevoArtesano = {
                      nombre: name,
                      identificacion: ide,
                      telefono: tel,
                      ciudad: $scope.ciudadSeleccionada,
                      image: 'src/utils/img/artesanos/Artesano 0.jpg'
                    };

                    $http.post(artesanosContext, nuevoArtesano)
                      .then(function success (response) {
                        nuevoArtesano = response.data;
                        $scope.artesanosRecords.push(nuevoArtesano);
                        $('#crearArtesano').modal('hide');
                        $scope.alerta = false;

                        document.getElementById('nombre').value = '';
                        document.getElementById('identificacion').value = '';
                        document.getElementById('telefono').value = '';

                        $scope.isExtraDetailRow = ($scope.artesanosRecords.length % 6 ) !== 0;//TODO
                      })
                      .catch(function error (response) {
                        document.getElementById('infoError').innerHTML = response.data;
                        $scope.alerta = true;
                      });
                  };

                  // Eliminar
                  $scope.removeArtesanos = function () {
                    $scope.toBeRemoved.forEach(function (item) {
                      $http.delete(artesanosContext + '/' + item.id);
                      var index = $scope.artesanosRecords.indexOf(item);
                      $scope.artesanosRecords.splice(index, 1);
                    });
                    $scope.shutRemoveOff();
                    $('#eliminarArtesanos').modal('hide');
                    $scope.isExtraDetailRow = ($scope.artesanosRecords.length % 6 ) !== 0;
                  };
                  $scope.toogleRemove = function () {
                    if ($scope.removeEnabled) {
                      // If removes is True, will Stop, if not, will go on until true is found or end of array
                      var removes = $scope.artesanosToRemove.some(function (item) {
                        return item;
                      });
                      if (removes) {
                        $scope.toBeRemoved = [];
                        $scope.artesanosToRemove.forEach(function (item, index) {
                          if (item) {
                            $scope.toBeRemoved.push($scope.artesanosRecords[index]);
                          }
                        });
                        $('#eliminarArtesanos').modal('show');
                      }
                      else {
                        $scope.shutRemoveOff();
                      }
                    }
                    else {
                      $scope.removeEnabled = true;
                      document.getElementById('btn-remove-artesanos').innerHTML = '&#128504;';

                      setDetails();
                      $scope.isDetailShown = false;
                      $state.go('artesanosList', {});
                    }
                  };
                  $scope.shutRemoveOff = function () {
                    initRemovedArtesanos();
                    $scope.removeEnabled = false;
                    document.getElementById('btn-remove-artesanos').innerHTML = '&#0215;';
                  };
                  $scope.addToRemove = function (index) {
                    $scope.artesanosToRemove[index] = !$scope.artesanosToRemove[index];
                  };

                  // Detail
                  $scope.seeDetail = function (index) {
                    if (!$scope.squareArtesanos[index]) {
                      var iRow = Math.ceil(( index + 1 ) / 6);

                      setDetails();
                      // if (!$scope.detailsRows[iRow]) {
                      initDetailsRows();
                      // }

                      $http.get(artesanosContext + '/' + $scope.artesanosRecords[index].id)
                        .then(function (response) {
                          $scope.currentArtesano = response.data;
                          $scope.detailsRows[iRow - 1] = !$scope.detailsRows[iRow - 1];
                          $scope.squareArtesanos[index] = true;
                        });
                    }
                    else {
                      // Se cierra
                      setDetails();
                      initDetailsRows();
                    }
                  };

                  // Funciones internas
                  function initRemovedArtesanos () {
                    $scope.artesanosToRemove = [];
                    $scope.artesanosRecords.forEach(function (item) {
                      $scope.artesanosToRemove.push(false);
                    });
                  }

                  function initDetailsRows () {
                    $scope.detailsRows = [];
                    for (var i = 0; i < $scope.numDetailsRows; i++) {
                      $scope.detailsRows.push(false);
                    }
                  }

                  function setDetails () {
                    $scope.squareArtesanos = [];
                    $scope.artesanosRecords.forEach(function (item) {
                      $scope.squareArtesanos.push(false);
                    });
                  }
                }]
            }
          }
        });
    }]);

  // DIRECTIVAS
  mod.directive('artesanoDetail', function () {
    return {
      restrict: 'E',
      templateUrl: 'src/modules/artesano/artesanos.detail.html'
    };
  });
  mod.directive('artesaniasList', function () {
    return {
      restrict: 'E',
      templateUrl: 'src/modules/artesania/artesanias.list.html',
      scope: {
        currentArtesano: '='
      }
    };
  });
  mod.directive('reviewsList', function () {
    return {
      restrict: 'E',
      templateUrl: 'src/modules/review/reviews.list.html',
      scope: {
        currentArtesano: '='
      }
    };
  });

  // CONTROLADORES
  mod.controller('artesanoDetailControlador', function ($scope, $http) {

    $scope.showArtesanias = true;
    $scope.showReviews = false;

    $scope.switchArtesanias = function () {
      $scope.showReviews = false;
      $scope.showArtesanias = true;
    };

    $scope.switchReviews = function () {
      $scope.showArtesanias = false;
      $scope.showReviews = true;
    };

    $scope.updateArtesano = function () {
      document.getElementById('upNombre').value = $scope.currentArtesano.nombre;
      document.getElementById('upIdentificacion').value = $scope.currentArtesano.identificacion;
      document.getElementById('upTelefono').value = $scope.currentArtesano.telefono;
      $scope.ciudadSeleccionada = $scope.currentArtesano.ciudad;

      $('#updateArtesano').modal('show');
    };
    $scope.confirmUpdate = function () {
      var artCur = $scope.currentArtesano;

      $scope.currentArtesano.id = artCur.id;
      $scope.currentArtesano.nombre = document.getElementById('upNombre').value;
      $scope.currentArtesano.identificacion = document.getElementById('upIdentificacion').value;
      $scope.currentArtesano.telefono = document.getElementById('upTelefono').value;
      $scope.currentArtesano.imagen = artCur.imagen;
      $scope.currentArtesano.ciudad = $scope.ciudadSeleccionada;

      $http.put('api/artesanos/' + artCur.id, $scope.currentArtesano)
        .then(function (response) {
          // $scope.currentArtesano = response.data;
          $('#updateArtesano').modal('hide');
        })
        .catch(function error (response) {
          document.getElementById('infoError').innerHTML = response.data;
          $scope.alerta = true;
        });
    };
  });
  mod.controller('artesanoArtesaniasControlador', function ($scope, $http) {

    // // Variables
    $scope.editArtesaniasEnabled = false;
    $scope.removeArtesaniasEnabled = false;
    $scope.artesaniasToRemove = []; // Boolean
    $scope.artesaniasOnQueue = []; // Entities

    // Init
    if (!isUndefined($scope.currentArtesano)) {
      resetRemoveOff();
    }

    // Crear
    $scope.crearArtesania = function () {
      var artesaniaNombre = document.getElementById('nombreCrearArtesania').value;
      var newArtesania = {
        nombre: artesaniaNombre,
        imagen: 'src/utils/img/artesanias/Artesania X.jpg' //TODO imagen
      };
      $http.post('api/artesanos/' + $scope.currentArtesano.id + '/artesanias/', newArtesania)
        .then(function (response) {
          newArtesania = response.data;
          $scope.currentArtesano.artesanias.push(newArtesania);
          $('#crearArtesania').modal('hide');
        });
    };

    // Editar
    $scope.switchEditArtesanias = function () {
      resetRemoveOff();
      $scope.editArtesaniasEnabled = !$scope.editArtesaniasEnabled;
    };
    $scope.editArtesania = function (index) {
      $scope.updatedArtesania = $scope.currentArtesano.artesanias[index];
      document.getElementById('updateNombreArtesania').value = $scope.updatedArtesania.nombre;
      if (!isUndefined($scope.updatedArtesania.imagen)) {
        document.getElementById('updateImagenArtesania').src = $scope.updatedArtesania.imagen;
      }
      $('#updateArtesania').modal('show');
    };
    $scope.confirmUpdateArtesania = function () {
      $scope.updatedArtesania.nombre = document.getElementById('updateNombreArtesania').value;
      //TODO Imagen
      $http.put('api/artesanos/' + $scope.currentArtesano.id + '/artesanias/' + $scope.updatedArtesania.id, $scope.updatedArtesania)
        .then(function (response) {
          $scope.updatedArtesania = response.data;
        });
      $('#updateArtesania').modal('hide');
    };

    // Eliminar
    $scope.switchRemoveArtesanias = function () {
      $scope.editArtesaniasEnabled = false;
      if ($scope.removeArtesaniasEnabled) {
        var willRemove = $scope.artesaniasToRemove.some(function (item) {
          return item;
        });
        if (willRemove) {
          $scope.artesaniasOnQueue = [];
          $scope.artesaniasToRemove.forEach(function (item, index) {
            if (item) {
              $scope.artesaniasOnQueue.push($scope.currentArtesano.artesanias[index]);
            }
          });
          $('#removeArtesanias').modal('show');
        }
        else {
          resetRemoveOff();
        }
      }
      else {
        document.getElementById('removeArtesaniasButton').innerHTML = '&#128504;';
        $scope.removeArtesaniasEnabled = true;
      }
      toogleUpdate();
    };
    $scope.prepareToRemoveArtesania = function (index) {
      $scope.artesaniasToRemove[index] = !$scope.artesaniasToRemove[index];
    };
    $scope.resetArtesanias = function () {
      resetRemoveOff();
    };
    $scope.deleteArtesanias = function () {
      $scope.artesaniasOnQueue.forEach(function (item) {
        $http.delete('api/artesanos/' + $scope.currentArtesano.id + '/artesanias/' + item.id);
        var index = $scope.currentArtesano.artesanias.indexOf(item);
        $scope.currentArtesano.artesanias.splice(index, 1);
        $('#removeArtesanias').modal('hide');
        resetRemoveOff();
      });
    };

    // Funciones Internas
    function resetRemoveOff () {
      $scope.artesaniasToRemove = [];
      $scope.currentArtesano.artesanias.forEach(function (item) {
        $scope.artesaniasToRemove.push(false);
      });

      $scope.removeArtesaniasEnabled = false;
      document.getElementById('removeArtesaniasButton').innerHTML = '&#0215;';
      toogleUpdate();
    }

    function toogleUpdate () {
      if (document.getElementById('updateArtesaniasButton').hasAttribute('disabled')) {
        document.getElementById('updateArtesaniasButton').removeAttribute('disabled');
      }
      else if ($scope.removeArtesaniasEnabled) {
        document.getElementById('updateArtesaniasButton').setAttribute('disabled', '');
      }
    }
  });
  mod.controller('artesanoReviewsControlador', function ($scope, $http) {
    $scope.currentStar = 0;

    $scope.eliminarReview = function (index) {
      $scope.reviewToRemove = $scope.currentArtesano.reviews[index];
      $('#eliminarReview').modal('show');
    };
    $scope.eliminarDefinitivo = function () {
      $http.delete('api/artesanos/' + $scope.currentArtesano.id + '/reviews/' + $scope.reviewToRemove.id);
      var index = $scope.currentArtesano.reviews.indexOf($scope.reviewToRemove);
      $scope.currentArtesano.reviews.splice(index, 1);
      $('#eliminarReview').modal('hide');
    };

    $scope.agregarReview = function () {
      var comment = document.getElementById('commentReview').value;

      var review = {
        comentario: comment,
        puntuacion: $scope.currentStar
      };
      $http.post('api/artesanos/' + $scope.currentArtesano.id + '/reviews', review)
        .then(function (response) {
          review = response.data;
          $scope.currentArtesano.reviews.push(review);
        });
    };

    $scope.openUpdateReview = function (index) {
      $scope.reviewToUpdate = $scope.currentArtesano.reviews[index];

      document.getElementById('upCommentReview').value = $scope.reviewToUpdate.comentario;
      $scope.upSetDefaultStar($scope.reviewToUpdate.puntuacion);

      $('#updateReview').modal('show');
    };
    $scope.confirmarUpdate = function () {
      var upComentario = document.getElementById('upCommentReview').value;
      var index = $scope.currentArtesano.reviews.indexOf($scope.reviewToUpdate);
      var id = $scope.reviewToUpdate.id;

      $scope.reviewToUpdate = {
        comentario: upComentario,
        puntuacion: $scope.currentStar
      };
      $http.put('api/artesanos/' + $scope.currentArtesano.id + '/reviews/' + id, $scope.reviewToUpdate)
        .then(function (response) {
          $scope.currentArtesano.reviews[index] = response.data;
        });
    };

    // Update
    $scope.upOverStar = function (s) {
      $scope.upResetStars();
      for (var i = 1; i <= s; i++) {
        document.getElementById('upStar' + i).style.color = '#dcb40a';
      }
    };
    $scope.upLeaveStar = function (s) {
      for (var i = s; i > 0; i--) {
        document.getElementById('upStar' + i).style.color = '#212121';
      }
    };
    $scope.upSetDefaultStar = function (s) {
      $scope.currentStar = s;
      $scope.upShowSelectedStar();
    };
    $scope.upShowSelectedStar = function () {
      if ($scope.currentStar !== 0) {
        $scope.upOverStar($scope.currentStar);
      }
    };
    $scope.upResetStars = function () {
      for (var i = 5; i > 0; i--) {
        document.getElementById('upStar' + i).style.color = '#212121';
      }
    };

    // Create
    $scope.overStar = function (s) {
      $scope.resetStars();
      for (var i = 1; i <= s; i++) {
        document.getElementById('star' + i).style.color = '#dcb40a';
      }
    };
    $scope.leaveStar = function (s) {
      for (var i = s; i > 0; i--) {
        document.getElementById('star' + i).style.color = '#212121';
      }
    };
    $scope.setDefaultStar = function (s) {
      $scope.currentStar = s;
      $scope.showSelectedStar();
    };
    $scope.showSelectedStar = function () {
      if ($scope.currentStar !== 0) {
        $scope.overStar($scope.currentStar);
      }
    };
    $scope.resetStars = function () {
      for (var i = 5; i > 0; i--) {
        document.getElementById('star' + i).style.color = '#212121';
      }
    };
  });

  function isUndefined (val) {
    return val === 'undefined' || val === '' || val === null || val === undefined;
  }

})(window.angular);