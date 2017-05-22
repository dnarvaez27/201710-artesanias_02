(function (ng) {
  var mod = ng.module('artesanoModule', ['ui.router']);

  mod.constant('artesanosContext', 'api/artesanos');
  mod.constant('ciudadesContext', 'api/ciudades');
  mod.constant('artesaniasContext', '/artesanias');
  mod.constant('reviewsContext', '/reviews');

  // STATES
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
                  // Child
                  $scope.reviewToRemove = {};

                  // Resolves
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
  mod.controller('artesanoDetailControlador', function ($scope, $http, artesanoService) {

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

      artesanoService.setArtesano($scope.currentArtesano);

      $('#updateArtesano').modal('show');
    };
    $scope.confirmUpdate = function () {
      var artCur = artesanoService.sArtesano;

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
          artesanoService.setArtesano(undefined);
        })
        .catch(function error (response) {
          document.getElementById('infoErrorUpdate').innerHTML = response.data;
          $scope.alerta = true;
        });
    };
  });
  mod.controller('artesanoArtesaniasControlador', function ($scope, $http, artesaniaService) {

    // // Variables
    $scope.editArtesaniasEnabled = false;
    $scope.removeArtesaniasEnabled = artesaniaService.isRemoving;
    $scope.artesaniasToRemove = []; // Boolean
    $scope.artesaniasOnQueue = artesaniaService.sArtesaniasOnQueue;

    // Init
    if (!isUndefined($scope.currentArtesano)) {
      resetRemoveOff();
    }

    // Crear
    $scope.prepareToAddArtesania = function () {
      artesaniaService.setCurrentArtesano($scope.currentArtesano);
      $('#crearArtesania').modal('show');
    };
    $scope.crearArtesania = function () {
      $scope.currentArtesano = artesaniaService.sCurrentArtesano;
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
      var temp = $scope.editArtesaniasEnabled;
      resetRemoveOff();
      $scope.editArtesaniasEnabled = !temp;
    };
    $scope.editArtesania = function (index) {
      artesaniaService.setCurrentArtesano($scope.currentArtesano);

      artesaniaService.setArtesania($scope.currentArtesano.artesanias[index]);
      document.getElementById('updateNombreArtesania').value = artesaniaService.sArtesania.nombre;
      if (!isUndefined(artesaniaService.sArtesania.imagen)) {
        document.getElementById('updateImagenArtesania').src = artesaniaService.sArtesania.imagen;
      }
      $('#updateArtesania').modal('show');
    };
    $scope.confirmUpdateArtesania = function () {
      $scope.currentArtesano = artesaniaService.sCurrentArtesano;

      artesaniaService.sArtesania.nombre = document.getElementById('updateNombreArtesania').value;
      //TODO Imagen
      $http.put('api/artesanos/' + $scope.currentArtesano.id + '/artesanias/' + artesaniaService.sArtesania.id, artesaniaService.sArtesania)
        .then(function (response) {
          artesaniaService.setArtesania(response.data);
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
          artesaniaService.sArtesaniasOnQueue = [];
          $scope.artesaniasToRemove.forEach(function (item, index) {
            if (item) {
              artesaniaService.sArtesaniasOnQueue.push($scope.currentArtesano.artesanias[index]);
            }
          });
          console.log(artesaniaService.sArtesaniasOnQueue);
          $('#removeArtesanias').modal('show');
          $scope.updateRemoves();
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
      $scope.currentArtesano = artesaniaService.sCurrentArtesano;
      artesaniaService.sArtesaniasOnQueue.forEach(function (item) {
        $http.delete('api/artesanos/' + $scope.currentArtesano.id + '/artesanias/' + item.id)
          .then(function (response) {
            var index = $scope.currentArtesano.artesanias.indexOf(item);
            $scope.currentArtesano.artesanias.splice(index, 1);
          });
      });
      $('#removeArtesanias').modal('hide');
      resetRemoveOff();
      $scope.editArtesaniasEnabled = false;
      $scope.removeArtesaniasEnabled = false;
      $scope.sArtesaniasOnQueue = [];
      $scope.artesaniasToRemove = [];
      artesaniaService.isRemoving = false;
    };
    $scope.updateRemoves = function () {
      $scope.artesaniasOnQueue = artesaniaService.sArtesaniasOnQueue;
      console.log($scope.artesaniasOnQueue);
    };

    function resetRemoveOff () {
      $scope.removeArtesaniasEnabled = false;
      $scope.editArtesaniasEnabled = false;
      $scope.artesaniasToRemove = [];
      $scope.currentArtesano.artesanias.forEach(function (item) {
        $scope.artesaniasToRemove.push(false);
      });
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
  mod.controller('artesanoReviewsControlador', function ($scope, $http, reviewService) {

    $scope.eliminarReview = function (index) {
      reviewService.setCurrentArtesano($scope.currentArtesano);
      reviewService.setReview($scope.currentArtesano.reviews[index]);

      document.getElementById('idReviewRemove').innerHTML = reviewService.sReview.id;
      document.getElementById('puntuacionReviewRemove').innerHTML = reviewService.sReview.puntuacion;
      document.getElementById('comentarioReviewRemove').innerHTML = reviewService.sReview.comentario;

      $('#eliminarReview').modal('show');
    };
    $scope.confirmarEliminarReview = function () {
      var review = reviewService.sReview;
      $scope.currentArtesano = reviewService.sCurrentArtesano;

      $http.delete('api/artesanos/' + $scope.currentArtesano.id + '/reviews/' + review.id);
      var index = $scope.currentArtesano.reviews.indexOf(review);
      $scope.currentArtesano.reviews.splice(index, 1);
      $('#eliminarReview').modal('hide');
    };

    $scope.prepareToAddReview = function () {
      reviewService.setCurrentArtesano($scope.currentArtesano);
      reviewService.setCurrentStar(0);
      $('#agregarReview').modal('show');
    };
    $scope.agregarReview = function () {
      $scope.currentArtesano = reviewService.sCurrentArtesano;

      var comment = document.getElementById('commentReview').value;

      var review = {
        comentario: comment,
        puntuacion: reviewService.sCurrentStar
      };
      $http.post('api/artesanos/' + $scope.currentArtesano.id + '/reviews', review)
        .then(function (response) {
          review = response.data;
          $scope.currentArtesano.reviews.push(review);
        });
    };

    $scope.openUpdateReview = function (index) {
      $scope.reviewToUpdate = $scope.currentArtesano.reviews[index];
      reviewService.sReview = $scope.currentArtesano.reviews[index];
      reviewService.setCurrentArtesano($scope.currentArtesano);

      document.getElementById('upCommentReview').value = $scope.reviewToUpdate.comentario;
      $scope.upSetDefaultStar($scope.reviewToUpdate.puntuacion);

      $('#updateReview').modal('show');
    };
    $scope.confirmarUpdateReview = function () {
      $scope.currentArtesano = reviewService.sCurrentArtesano;

      var upComentario = document.getElementById('upCommentReview').value;
      var index = $scope.currentArtesano.reviews.indexOf(reviewService.sReview);
      var id = reviewService.sReview.id;

      $scope.reviewToUpdate = {
        comentario: upComentario,
        puntuacion: reviewService.sCurrentStar
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
      reviewService.setCurrentStar(s);
      $scope.upShowSelectedStar();
    };
    $scope.upShowSelectedStar = function () {
      if (reviewService.sCurrentStar !== 0) {
        $scope.upOverStar(reviewService.sCurrentStar);
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
      reviewService.setCurrentStar(s);
      $scope.showSelectedStar();
    };
    $scope.showSelectedStar = function () {
      if (reviewService.sCurrentStar !== 0) {
        $scope.overStar(reviewService.sCurrentStar);
      }
    };
    $scope.resetStars = function () {
      for (var i = 5; i > 0; i--) {
        document.getElementById('star' + i).style.color = '#212121';
      }
    };
  });

  // SERVICES
  mod.service('artesanoService', function () {
    this.sArtesano = {};
    this.setArtesano = function (art) {
      this.sArtesano = art;
    };
  });
  mod.service('reviewService', function () {
    this.sReview = {};
    this.sCurrentArtesano = {};
    this.sCurrentStar = 0;

    this.setCurrentStar = function (s) {
      this.sCurrentStar = s;
    };
    this.setReview = function (rev) {
      this.sReview = rev;
    };
    this.setCurrentArtesano = function (art) {
      this.sCurrentArtesano = art;
    };
  });
  mod.service('artesaniaService', function () {
    this.sArtesania = {};
    this.sCurrentArtesano = {};
    this.sArtesaniasOnQueue = []; // Entities
    this.isRemoving = false;

    this.setArtesania = function (artesania) {
      this.sArtesania = artesania;
    };
    this.setCurrentArtesano = function (artesano) {
      this.sCurrentArtesano = artesano;
    };
  });

  // FUNCIONES
  function isUndefined (val) {
    return val === 'undefined' || val === '' || val === null || val === undefined;
  }

})(window.angular);