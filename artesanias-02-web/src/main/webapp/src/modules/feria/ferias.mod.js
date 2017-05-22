(function (ng) {
  var mod = ng.module('feriaModule', ['ui.router']);
  mod.constant('feriasContext', 'api/ferias');
  mod.constant('ciudadesContext', 'api/ciudades');
  mod.constant('espaciosContext', 'espacios');
  mod.constant('artesaniasContext', '/artesanias');
  mod.constant('reviewsContext', '/reviews');
  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/feria/';
      $urlRouterProvider.otherwise('/ferias/list');
      $stateProvider
        .state('ferias', {
          url: '/ferias',
          abstract: true,
          resolve: {
            ferias: ['$http', 'feriasContext',
              function ($http, feriasContext) {
                return $http.get(feriasContext);
              }]
          },
          views: {
            'mainView': {
              templateUrl: basePath + 'ferias.html',
              controller: ['$scope', 'ferias',
                function ($scope, ferias) {
                  $scope.feriasRecords = ferias.data;
                }]
            }
          }
        })
        .state('feriasList', {
          url: '/list',
          parent: 'ferias',
          resolve: {
              espacios: ['$http', 'ciudadesContext', 'espaciosContext',
              function ($http, ciudadesContext, espaciosContext) {
                    var sites = [];
                    $http.get(ciudadesContext).then(
                          function (response) {
                              response.data.forEach(function (c) {
                                  $http.get(ciudadesContext+'/'+c.id+'/'+espaciosContext).then(
                                   function (response) {
                                       response.data.forEach(function (e) {
                                           sites.push(e);
                                       });
                                   });
                              });
                          });
                    return sites;
              }]
          },
          views: {
            'listView': {
              templateUrl: basePath + 'feria.list.html',
              controller: ['$scope', '$http', 'feriasContext', '$state', 'espacios',
                function ($scope, $http, feriasContext, $state, espacios) {
                  // Variables
                  $scope.isExtraDetailRow = ($scope.feriasRecords.length % 6 ) !== 0;
                  $scope.numDetailsRows = Math.ceil($scope.feriasRecords.length / 6);
                  $scope.detailsRows = [];
                  $scope.alerta = false;
                  $scope.removeEnabled = false;
                  $scope.feriasToRemove = [];
                  $scope.squareFerias = [];
                  $scope.isDetailShown = true;
                  $scope.sites = espacios;
                  // Init
                  initDetailsRows();
                  initRemovedFerias();
                  // Control Detail
                  $scope.isDetailRowShown = function (index) {
                    var i = Math.ceil(( index + 1 ) / 6);
                    return $scope.detailsRows[i-1];
                  };
                  $scope.isLastRowDetail = function () {
                    return $scope.detailsRows[$scope.detailsRows.length-1];
                  };
                  // Crear
                  $scope.crear = function () {
                    setDetails();
                    $scope.isDetailShown = false;
                    $state.go('feriasList', {});
                    var name = document.getElementById('nombre').value;
                    var ini = document.getElementById('inicio').value;
                    var fn = document.getElementById('fin').value;
                    var tb = document.getElementById('totalb').value;
                    var dr = 1.0;
                    var dm = (100 - document.getElementById('dmenores').value)/100;
                    var dM = (100 - document.getElementById('dmayores').value)/100;
                    var nuevaFeria = {
                      nombre: name,
                      inicio: ini,
                      fin: fn,
                      espacio: $scope.espacioSeleccionado,
                      totalBoletas: tb,
                      descuentoMenores: dm,
                      descuentoMayores: dM,
                      descuentoRegular: dr,
                      image: 'https://dummyimage.com/1524x2000/948e94/fff.jpg'
                    };
                    console.log(nuevaFeria);
                    $http.post(feriasContext, nuevaFeria)
                      .then(function (response) {
                        nuevaFeria = response.data;
                        $scope.feriasRecords.push(nuevaFeria);
                        $('#crearFeria').modal('hide');
                        $scope.alerta = false;
                        document.getElementById('nombre').value = '';
                        document.getElementById('inicio').value = '';
                        document.getElementById('fin').value = '';
                        document.getElementById('totalb').value = '';
                        document.getElementById('dmenores').value = '';
                        document.getElementById('dmayores').value = '';

                        $scope.isExtraDetailRow = ($scope.feriasRecords.length % 6 ) !== 0;//TODO
                      })
                      .catch(function (response) {
                        document.getElementById('infoError').innerHTML = response.data;
                        $scope.alerta = true;
                      });
                  };
                  // Eliminar
                  $scope.removeFerias = function () {
                    $scope.toBeRemoved.forEach(function (item) {
                      $http.delete(feriasContext + '/' + item.id);
                      var index = $scope.feriasRecords.indexOf(item);
                      $scope.feriasRecords.splice(index, 1);
                    });
                    $scope.shutRemoveOff();
                    $('#eliminarFerias').modal('hide');
                    $scope.isExtraDetailRow = ($scope.feriasRecords.length % 6 ) !== 0;
                  };
                  $scope.toogleRemove = function () {
                    if ($scope.removeEnabled) {
                      // If removes is True, will Stop, if not, will go on until true is found or end of array
                      var removes = $scope.feriasToRemove.some(function (item) {
                        return item;
                      });
                      if (removes) {
                        $scope.toBeRemoved = [];
                        $scope.feriasToRemove.forEach(function (item, index) {
                          if (item) {
                            $scope.toBeRemoved.push($scope.feriasRecords[index]);
                          }
                        });
                        $('#eliminarFerias').modal('show');
                      } else {
                        $scope.shutRemoveOff();
                      }
                    } else {
                      $scope.removeEnabled = true;
                      document.getElementById('btn-remove-ferias').innerHTML = '&#128504;';

                      setDetails();
                      $scope.isDetailShown = false;
                      $state.go('feriasList', {});
                    }
                  };
                  $scope.shutRemoveOff = function () {
                    initRemovedFerias();
                    $scope.removeEnabled = false;
                    document.getElementById('btn-remove-ferias').innerHTML = '&#0215;';
                  };
                  $scope.addToRemove = function (index) {
                    $scope.feriasToRemove[index] = !$scope.feriasToRemove[index];
                  };
                  // Detail
                  $scope.seeDetail = function (index) {
                    if (!$scope.squareFerias[index]) {
                      var iRow = Math.ceil(( index + 1 ) / 6);
                      setDetails();
                      initDetailsRows();
                      $http.get(feriasContext + '/' + $scope.feriasRecords[index].id)
                        .then(function (response) {
                          $scope.currentFeria = response.data;
                          $scope.detailsRows[iRow - 1] = !$scope.detailsRows[iRow - 1];
                          $scope.squareFerias[index] = true;
                        });
                    } else {
                      // Se cierra
                      setDetails();
                      initDetailsRows();
                    }
                  };
                  // Funciones internas
                  function initRemovedFerias () {
                    $scope.feriasToRemove = [];
                    $scope.feriasRecords.forEach(function (item) {
                      $scope.feriasToRemove.push(false);
                    });
                  }
                  function initDetailsRows () {
                    $scope.detailsRows = [];
                    for (var i = 0; i < $scope.numDetailsRows; i++) {
                      $scope.detailsRows.push(false);
                    }
                  }
                  function setDetails () {
                    $scope.squareFerias = [];
                    $scope.feriasRecords.forEach(function (item) {
                      $scope.squareFerias.push(false);
                    });
                  }
                }]
            }
          }
        });
    }]);
  // DIRECTIVAS
  mod.directive('feriaDetail', function () {
    return {
      restrict: 'E',
      templateUrl: 'src/modules/feria/feria.detail.html'
    };
  });
  mod.directive('espacioDetail', function () {
    return {
      restrict: 'E',
      templateUrl: 'src/modules/espacio/espacio.detail.html',
      scope: {
        espacio: '=espacio'
      }
    };
  });
  mod.directive('boletasList', function () {
    return {
      restrict: 'E',
      templateUrl: 'src/modules/boleta/boleta.list.html',
      scope: {
        boletasRecords: '=boletas',
        currentFeria: '=feria'
      }
    };
  });
  // CONTROLADORES
  mod.controller('feriaDetailControlador', function ($scope, $http) {
    $scope.showBoletas = true;
    $scope.showEspacio = false;
    $scope.switchBoletas = function () {
      $scope.showEspacio = false;
      $scope.showBoletas = true;
    };
    $scope.switchEspacio = function () {
      $scope.showBoletas = false;
      $scope.showEspacio = true;
    };
    $scope.updateFeria = function () {
        document.getElementById('unombre').value   = $scope.currentFeria.nombre;
        document.getElementById('uinicio').value   = $scope.currentFeria.inicio;
        document.getElementById('ufin').value      = $scope.currentFeria.fin;
        document.getElementById('utotalb').value   = $scope.currentFeria.totalBoletas;
        document.getElementById('udmenores').value = 100 - $scope.currentFeria.descuentoMenores*100;
        document.getElementById('udmayores').value = 100 - $scope.currentFeria.descuentoMayores*100;
        $scope.espacioSeleccionado = $scope.currentFeria.espacio;
        console.log($scope.currentFeria.espacio);
        $('#updateFeria').modal('show');
    };
    $scope.confirmUpdate = function () {
      var curFe = $scope.currentFeria;
      $scope.currentFeria.id = curFe.id;
      $scope.currentFeria.nombre = document.getElementById('unombre').value;
      if (document.getElementById('uinicio').value === $scope.currentFeria.inicio) {
          $scope.currentFeria.inicio = null;
      } else {
          $scope.currentFeria.inicio = document.getElementById('uinicio').value;
      }
      if (document.getElementById('ufin').value === $scope.currentFeria.inicio) {
          $scope.currentFeria.fin = null;
      } else {
          $scope.currentFeria.inicio = document.getElementById('ufin').value;
      }
      $scope.currentFeria.totalBoletas = document.getElementById('utotalb').value;
      $scope.currentFeria.descuentoMenores = (100 - document.getElementById('udmenores').value)/100;
      $scope.currentFeria.descuentoMayores = (100 - document.getElementById('udmayores').value)/100;
      $scope.currentFeria.descuentoRegular = curFe.descuentoRegular;
      $scope.currentFeria.imagen = curFe.imagen;
      $http.put('api/ferias/' + curFe.id, $scope.currentFeria)
        .then(function (response) {
          $scope.currentFeria = response.data;
          $('#updateFeria').modal('hide');
        })
        .catch(function (response) {
          document.getElementById('infoError').innerHTML = response.data;
          $scope.alerta = true;
        });
    };
  });
  mod.controller('boletaControlador', function ($scope, $http) {
    // // Variables
    $scope.editBoletasEnabled = false;
    $scope.removeBoletasEnabled = false;
    $scope.boletasToRemove = []; // Boolean
    $scope.boletasOnQueue = []; // Entities
    // Init
    if (!isUndefined($scope.currentFeria)) {
      resetRemoveOff();
    }
    $scope.darPrecioBoleta = function (boleta) {
        return boleta.tipo === 1 ? boleta.precio*$scope.currentFeria.descuentoMenores : 
                boleta.tipo === 2 ? boleta.precio*$scope.currentFeria.descuentoRegular :
                boleta.precio*$scope.currentFeria.descuentoMayores;
    };

    // Crear
    $scope.crearBoleta = function () {
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
    $scope.switchEditBoletas = function () {
      resetRemoveOff();
      $scope.editBoletasEnabled = !$scope.editBoletasEnabled;
    };
    
    $scope.editBoleta = function (index) {
      $scope.updatedArtesania = $scope.currentArtesano.artesanias[index];
      document.getElementById('updateNombreBoleta').value = $scope.updatedArtesania.nombre;
      if (!isUndefined($scope.updatedArtesania.imagen)) {
        document.getElementById('updateImagenBoleta').src = $scope.updatedArtesania.imagen;
      }
      $('#updateBoleta').modal('show');
    };
    $scope.confirmUpdateArtesania = function () {
      $scope.updatedArtesania.nombre = document.getElementById('updateNombreArtesania').value;
      //TODO Imagen
      $http.put('api/ferias/' + $scope.currentFeria.id + '/boletas/' + $scope.updatedArtesania.id, $scope.updatedArtesania)
        .then(function (response) {
          $scope.updatedArtesania = response.data;
        });
      $('#updateBoleta').modal('hide');
    };

    // Eliminar
    $scope.switchRemoveBoletas = function () {
      $scope.editBoletasEnabled = false;
      if ($scope.removeBoletasEnabled) {
        var willRemove = $scope.boletasToRemove.some(function (item) {
          return item;
        });
        if (willRemove) {
          $scope.boletasOnQueue = [];
          $scope.boletasToRemove.forEach(function (item, index) {
            if (item) {
              $scope.boletasOnQueue.push($scope.currentFeria.boletas[index]);
            }
          });
          $('#removeBoletas').modal('show');
        } else {
          resetRemoveOff();
        }
      } else {
        document.getElementById('removeBoletasButton').innerHTML = '&#128504;';
        $scope.removeBoletasEnabled = true;
      }
      toogleUpdate();
    };
    $scope.prepareToRemoveBoleta = function (index) {
      $scope.boletasToRemove[index] = !$scope.boletasToRemove[index];
    };
    $scope.resetBoletas = function () {
      resetRemoveOff();
    };
    $scope.deleteBoletas = function () {
      $scope.artesaniasOnQueue.forEach(function (item) {
        $http.delete('api/ferias/' + $scope.currentFeria.id + '/boletas/' + item.id);
        var index = $scope.currentFeria.boletas.indexOf(item);
        $scope.currentFeria.boletas.splice(index, 1);
        $('#removeBoletas').modal('hide');
        resetRemoveOff();
      });
    };

    // Funciones Internas
    function resetRemoveOff () {
      $scope.boletasToRemove = [];
      $scope.currentFeria.boletas.forEach(function (item) {
        $scope.boletasToRemove.push(false);
      });

      $scope.removeBoletasEnabled = false;
      document.getElementById('removeBoletasButton').innerHTML = '&#0215;';
      toogleUpdate();
    }

    function toogleUpdate () {
      if (document.getElementById('updateBoletasButton').hasAttribute('disabled')) {
        document.getElementById('updateBoletasButton').removeAttribute('disabled');
      }
      else if ($scope.removeArtesaniasEnabled) {
        document.getElementById('updateBoletasButton').setAttribute('disabled', '');
      }
    }
  });
  mod.controller('espacioControlador', function ($scope, $http) {
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
  });
  function isUndefined (val) {
    return val === 'undefined' || val === '' || val === null || val === undefined;
  }
})(window.angular);