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
          artesanos: ['$http', 'artesanosContext', function ($http, artesanosContext) {
            // return $http.get('data/artesanos.json')
            return $http.get(artesanosContext)
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
        resolve: {
          currentArtesano: ['$http', 'artesanosContext', '$stateParams', function ($http, artesanosContext, $params) {
            return $http.get(artesanosContext + '/' + $params.artesanoId)
          }]
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'artesanos.detail.html',
            controller: ['$scope', '$stateParams', 'currentArtesano', function ($scope, $params, currentArtesano) {
              // $scope.currentArtesano = $scope.artesanosRecords[$params.artesanoId - 1]
              $scope.currentArtesano = currentArtesano.data
            }]
          }
        }
      })
      .state('artesanoDetailArtesanias', {
        url: '/artesanias',
        parent: 'artesanoDetail',
        resolve: {
          artesanias: ['$http', 'artesanosContext', '$stateParams', function ($http, artesanosContext, $params) {
            return $http.get(artesanosContext + '/' + $params.artesanoId + '/artesanias')
          }]
        },
        views: {
          'detail': {
            templateUrl: basePath + 'artesanos.detail.artesanias.html'
            // ,
            // controller: ['$scope','artesanias', function ($scope, artesanias) {
            //   $scope.currentArtesano.artesanias = artesanias.data
            // }]
          }
        }
      })
      .state('artesanoDetailReviews', {
        url: '/reviews',
        parent: 'artesanoDetail',
        resolve: {
          reviews: ['$http', 'artesanosContext', '$stateParams', function ($http, artesanosContext, $params) {
            return $http.get(artesanosContext + '/' + $params.artesanoId + '/reviews')
          }]
        },
        views: {
          'detail': {
            templateUrl: basePath + 'artesanos.detail.reviews.html'
            // ,
            // controller: ['$scope', 'reviews', function ($scope, reviews) {
            //   $scope.currentArtesano.reviews = reviews.data
            // }]
          }
        }
      })
  }])
})(window.angular)