(function (ng) {
  var mod = ng.module('espacioModule', ['ui.router']);

  mod.constant('espaciosContext', 'api/espacios');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/espacio/';
      var basePathPabellones = 'src/modules/pabellon/';

      $urlRouterProvider.otherwise('/espacios/list');

      $stateProvider
        .state('espacios', {
          url: '/espacios',
          abstract: true,
          resolve: {
            espacios: ['$http', 'espaciosContext',
              function ($http, espaciosContext) {
                return $http.get(espaciosContext);
              }]
          },
          views: {
            'mainView': {
              templateUrl: basePath + 'espacios.html',
              controller: ['$scope', 'espacios',
                function ($scope, espacios) {
                  $scope.espaciosRecords = espacios.data;
                }]
            }
          }
        })
        .state('espaciosList', {
          url: '/list',
          parent: 'espacios',
          views: {
            'listView': {
              templateUrl: basePath + 'espacio.list.html',
              controller: ['$http', 'espaciosContext',
                function ($http, espaciosContext) {

                  function add (id, espacios) {
                    $http.post(espaciosContext + '/' + id, espacio);
                  }

                  function del (id) {
                    $http.delete(espaciosContext + '/' + id);
                  }

                  //TODO
                  function crea () {
                    var name = document.getElementById('nombre').innerHTML;
                    var ciudad = document.getElementById('ciudad').innerHTML;

                    var nuevoEspacio = {
                      nombre: name,
                      ciudad: ciudad
                    };

                    $http.post(espaciosContext, nuevoEspacio);

                  }
                }]
            }
          }
        })
        .state('espacioDetail', {
          url: '/{espacioId:int}/detail',
          parent: 'espaciosList',
          param: {
            espacioId: null
          },
          resolve: {
            currentEspacio: ['$http', 'espaciosContext', '$stateParams',
              function ($http, espaciosContext, $params) {
                return $http.get(espaciosContext + '/' + $params.espacioId);
              }]
          },
          views: {
            'detailView': {
              templateUrl: basePath + 'espacio.detail.html',
              controller: ['$scope', '$stateParams', 'currentEspacio',
                function ($scope, $params, currentEspacio) {
                  $scope.currentEspacio = currentEspacio.data;
                  $scope.pabellonesRecords = $scope.currentEspacio.pabellones;
                }]
            }
          }
        })
        .state('espacioDetailPabellones', {
          url: '/pabellones',
          parent: 'espacioDetail',
          views: {
            'detail': {
              templateUrl: basePathPabellones + 'pabellones.list.html'
            }
          }
        });
    }]);
})(window.angular);
