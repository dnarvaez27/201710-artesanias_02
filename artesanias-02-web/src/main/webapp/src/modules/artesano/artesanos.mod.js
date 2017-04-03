(function (ng) {
  var mod = ng.module('artesanoModule', ['ui.router'])
  mod.constant('artesanosContext', 'api/artesanos')
  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/artesano/'
    $urlRouterProvider.otherwise('/artesanosList')

    $stateProvider
      .state('artesanos', {
        url: '/artesanos',
        abstract: true,
        resolve: {
          artesanos: ['$http', function ($http) {
            //  return $http.get( artesanosContext )
            return $http.get('data/artesanos.json')
          }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'artesanos.html',
            controller: ['$scope', 'artesanos', function ($scope, artesanos) {
              $scope.artesanosRecords = artesanos.data
            }]
          }
        }
      })
      .state('artesanosList', {
        url: '/list',
        parent: 'artesanos',
        views: {
          'listView': {
            templateUrl: basePath + 'artesanos.list.html'
          }
        }
      })
      .state('artesanoDetail', {
        url: '/{artesanoId:int}/detail',
        parent: 'artesanosList',
        param: {
          artesanoId: null
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'artesanos.detail.html',
            controller: ['$scope', '$stateParams', function ($scope, $params) {
              $scope.currentArtesano = $scope.artesanosRecords[$params.artesanoId - 1]
            }]
          }
        }
      })
      .state('artesanoDetailArtesanias', {
        url: '/artesanias',
        parent: 'artesanoDetail',
        views: {
          'detail': {
            templateUrl: basePath + 'artesanos.detail.artesanias.html'
          }
        }
      })
      .state('artesanoDetailReviews', {
        url: '/reviews',
        parent: 'artesanoDetail',
        param: {},
        views: {
          'detail': {
            templateUrl: basePath + 'artesanos.detail.reviews.html'
          }
        }
      })
  }])
})(window.angular)