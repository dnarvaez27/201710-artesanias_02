(function (ng) {
  var mod = ng.module('pabellonModule', ['ui.router'])
  mod.constant('pabellonesContext', 'api/pabellones')
  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/pabellon/'
    $urlRouterProvider.otherwise('/pabellonesList')

    $stateProvider
      .state('pabellones', {
        url: '/pabellones',
        abstract: true,
        resolve: {
          artesanos: ['$http', function ($http) {
            //  return $http.get( artesanosContext )
            return $http.get('data/pabellones.json')
          }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'pabellones.html',
            controller: ['$scope', 'pabellones', function ($scope, pabellones) {
              $scope.pabellonesRecords = pabellones.data
            }]
          }
        }
      })
      .state('pabellonesList', {
        url: '/list',
        parent: 'pabellones',
        views: {
          'listView': {
            templateUrl: basePath + 'pabellones.list.html'
          }
        }
      })
      .state('pabellonDetail', {
        url: '/{pabellonId:int}/detail',
        parent: 'pabellonesList',
        param: {
          pabellonId: null
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'pabellones.detail.html',
            controller: ['$scope', '$stateParams', function ($scope, $params) {
              $scope.currentPabellon = $scope.pabellonesRecords[$params.pabellonId - 1]
            }]
          }
        }
      })
      .state('pabellonDetailStands', {
        url: '/stands',
        parent: 'pabellonDetail',
        views: {
          'detail': {
            templateUrl: basePath + 'pabellones.detail.stands.html'
          }
        }
      })
      .state('pabellonDetailStands', {
        url: '/stands',
        parent: 'pabellonDetail',
        param: {},
        views: {
          'detail': {
            templateUrl: basePath + 'pabellones.detail.stands.html'
          }
        }
      })
  }])
})(window.angular)