(function (ng) {
  var mod = ng.module('ciudadModule', ['ui.router']);

  mod.constant('ciudadesContext', 'api/ciudades');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/ciudad/';
      var basePathEspacios = 'src/modules/espacio/';

      $urlRouterProvider.otherwise('/ciudades/list');

      $stateProvider
        .state('ciudadess', {
          url: '/ciudades',
          abstract: true,
          resolve: {
            ciudades: ['$http', 'ciudadesContext',
              function ($http, ciudadesContext) {
                return $http.get(ciudadesContext);
              }]
          },
          views: {
            'mainView': {
              templateUrl: basePath + 'ciudades.html',
              controller: ['$scope', 'ciudades',
                function ($scope, ciudades) {
                  $scope.ciudadesRecords = ciudades.data;
                }]
            }
          }
        })
        .state('ciudadesList', {
          url: '/list',
          parent: 'ciudades',
          views: {
            'listView': {
              templateUrl: basePath + 'ciudades.list.html',
              controller: ['$http', 'ciudadesContext',
                function ($http, ciudadesContext) {

                  function add (id, ciudad) {
                    $http.post(ciudadesContext + '/' + id, ciudad);
                  }

                  function del (id) {
                    $http.delete(ciudadesContext + '/' + id);
                  }

                  //TODO
                  function crea () {
                    var name = document.getElementById('nombre').innerHTML;
                    var pais = document.getElementById('pais').innerHTML;

                    var nuevaCiudad = {
                      nombre: name,
                      pais: pais
                    };

                    $http.post(ciudadesContext, nuevaCiudad);

                  }
                }]
            }
          }
        })
        .state('ciudadDetail', {
          url: '/{ciudadId:int}/detail',
          parent: 'ciudadesList',
          param: {
            ciudadId: null
          },
          resolve: {
            currentCiudad: ['$http', 'ciudadesContext', '$stateParams',
              function ($http, ciudadesContext, $params) {
                return $http.get(ciudadesContext + '/' + $params.ciudadId);
              }]
          },
          views: {
            'detailView': {
              templateUrl: basePath + 'ciudades.detail.html',
              controller: ['$scope', '$stateParams', 'currentCiudad',
                function ($scope, $params, currentCiudad) {
                  $scope.currentCiudad = currentCiudad.data;
                  $scope.espaciossRecords = $scope.currentCiudad.espacios;
                }]
            }
          }
        })
        .state('ciudadDetailEspacios', {
          url: '/espacios',
          parent: 'ciudadDetail',
          views: {
            'detail': {
              templateUrl: basePathEspacios + 'espacios.list.html'
            }
          }
        });
    }]);
})(window.angular);
