(function (ng) {
  var mod = ng.module('boletaModule', ['ui.router'])
  mod.constant('boletasContext', 'api/ferias')
  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/boletas/'
    $urlRouterProvider.otherwise('/feriasList')

    $stateProvider
      .state('ferias', {
        url: '/ferias',
        abstract: true,
        resolve: {
          ferias: ['$http', function ($http) {
            return $http.get('data/ferias.json')
          }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'ferias.html',
            controller: ['$scope', 'ferias', function ($scope, ferias) {
              $scope.feriasRecords = ferias.data
            }]
          }
        }
      })
      .state('feriasList', {
        url: '/list',
        parent: 'ferias',
        views: {
          'listView': {
            templateUrl: basePath + 'ferias.list.html'
          }
        }
      })
      .state('feriaDetail', {
        url: '/{idFeria:int}/detail',
        parent: 'feriasList',
        param: {
          idFeria: null
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'feria.detail.html',
            controller: ['$scope', '$stateParams', function ($scope, $params) {
              $scope.currentFeria = $scope.feriasRecords[$params.idFeria - 1]
            }]
          }
        }
      })
  }])
})(window.angular)