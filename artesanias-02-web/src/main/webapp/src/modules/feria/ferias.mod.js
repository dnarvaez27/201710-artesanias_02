(function (ng) {
  var mod = ng.module('feriaModule', ['ui.router']);
  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/ferias/';
    $urlRouterProvider.otherwise('/feriasList');
    $stateProvider
      .state('ferias', {
        url: '/ferias',
        abstract: true,
        param: {
            context: 'api/ferias',
            idParent: null
        },
        resolve: {
          ferias: ['$http', '$stateParams', function ($http, $params) {
            if ($params.context !== 'api/ferias') {
                return $http.get($params.context+'/'+ $params.idParent+'/ferias');
            }
            return $http.get($params.context);
          }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'ferias.html',
            controller: ['$scope', 'ferias', function ($scope, ferias) {
              $scope.feriasRecords = ferias.data;
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
          idFeria: 0
        },
        resolve: {
          currentFeria: ['$http', '$stateParams', function ($http, $params) {
            if ($params.context !== 'api/ferias') {
                return $http.get($params.context+'/'+ $params.idParent+'/ferias'+'/'+$params.idFeria);
            }
            return $http.get($params.context+'/'+$params.idFeria);
          }]
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'feria.detail.html',
            controller: ['$scope', 'currentFeria', function ($scope, currentFeria) {
              $scope.currentFeria = currentFeria.data;
            }]
          }
        }
      });
  }]);
})(window.angular);