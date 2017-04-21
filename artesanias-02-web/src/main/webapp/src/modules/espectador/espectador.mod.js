(function (ng) {
  var mod = ng.module('espectadorModule', ['ui.router']);

  mod.constant('espectadorContext', 'api/espectadores');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/espectador/';
      $urlRouterProvider.otherwise('/espectadores/list');

      $stateProvider
        .state('espectador', {
          url: '/espectadores',
          abstract: true,
          views: {
            'mainView': {
              templateUrl: basePath + 'espectador.html'
            }
          }
        })
        .state('espectadoresList', {
          url: '/list',
          parent: 'espectador',
          resolve: {
            espectadores: ['$http', 'espectadorContext',
              function ($http, espectadorContext) {
                return $http.get(espectadorContext);
              }]
          },
          views: {
            'listView': {
              templateUrl: basePath + 'espectador.list.html',
              controller: ['$scope', 'espectadores', '$http', 'espectadorContext',
                function ($scope, espectadores, $http, espectadorContext) {
                  $scope.espectadoresRecords = espectadores.data;

                  $scope.getEspectador = function (espectadorId) {
                    $http.get(espectadorContext + '/' + espectadorId)
                      .then(function (response) {
                          $scope.currentEspectador = response.data;
                        }
                      );
                  };

                  $scope.revealPass = function () {
                    document.getElementById('pass').type = 'text';
                  };
                  $scope.hidePass = function () {
                    document.getElementById('pass').type = 'password';
                  };
                }]
            }
          }
        })
        .state('espectadorDetail', {
          url: '{espectadorId: int}/detail',
          parent: 'espectador',
          param: {
            espectadorId: null
          },
          resolve: {
            espectador: ['$http', 'espectadorContext', '$stateParams',
              function ($http, espectadorContext, $stateParams) {
                return $http.get(espectadorContext + '/' + $stateParams.espectadorId);
              }]
          },
          views: {
            'detailView': {
              templateUrl: basePath + 'espectador.detail.html',
              controller: ['$scope', 'espectador',
                function ($scope, espectador) {
                  $scope.currentEspectador = espectador.data;
                }]
            }
          }
        });
    }]);
})(window.angular);