(function (ng) {
  var mod = ng.module('boletaModule', ['ui.router']);
  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/boletas/';
    $urlRouterProvider.otherwise('/boletasList');
    $stateProvider
      .state('boletas', {
        url: '/boletas',
        abstract: true,
        param: {
            context: 'api/ferias',
            idParent: 1
        },
        resolve: {
          boletas: ['$http', '$stateParams', function ($http, $params) {
            return $http.get($params.context+'/'+$params.idParent+'/boletas');
          }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'boletas.html',
            controller: ['$scope', 'boletas', function ($scope, boletas) {
              $scope.boletasRecords = boletas.data
            }]
          }
        }
      })
      .state('boletasList', {
        url: '/list',
        parent: 'boletas',
        views: {
          'listView': {
            templateUrl: basePath + 'boletas.list.html'
          }
        }
      })
      .state('boletaDetail', {
        url: '/{idBoleta:int}/detail',
        parent: 'boletas',
        param: {
          idBoleta: null
        },
        resolve: {
          currentBoleta: ['$http', '$stateParams', function ($http, $params) {
            return $http.get($params.context+'/'+$params.idParent+'/boletas/'+$params.idBoleta);
          }]
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'boleta.detail.html',
            controller: ['$scope', 'currentBoleta', function ($scope, currentBoleta) {
              $scope.currentBoleta = currentBoleta;
            }]
          }
        }
      });
  }]);
})(window.angular);