(function (ng) {
  var mod = ng.module('artesanoModule', ['ui.router']);

  mod.constant('artesanosContext', 'api/artesanos');

  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/artesano/';
    var basePathArtesanias = 'src/modules/artesania/';
    var basePathReviews = 'src/modules/review/';
    var basePathFerias = 'src/modules/feria/';

    $urlRouterProvider.otherwise('/artesanosList');

    $stateProvider
      .state('artesanos', {
        url: '/artesanos',
        abstract: true,
        resolve: {
          artesanos: ['$http', 'artesanosContext', function ($http, artesanosContext) {
            // return $http.get('data/artesanos.json')
            return $http.get(artesanosContext);
          }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'artesanos.html',
            controller: ['$scope', 'artesanos', function ($scope, artesanos) {
              $scope.artesanosRecords = artesanos.data;
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
          currentArtesano: ['$http', 'artesanosContext', '$stateParams',
            function ($http, artesanosContext, $params) {
              return $http.get(artesanosContext + '/' + $params.artesanoId);
            }]
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'artesanos.detail.html',
            controller: ['$scope', '$stateParams', 'currentArtesano',
              function ($scope, $params, currentArtesano) {
                $scope.currentArtesano = currentArtesano.data;
                $scope.artesaniasRecords = $scope.currentArtesano.artesanias;
                $scope.reviewsRecords = $scope.currentArtesano.reviews;
                $scope.feriasRecords = $scope.currentArtesano.ferias;
              }]
          }
        }
      })
      .state('artesanoDetailArtesanias', {
        url: '/artesanias',
        parent: 'artesanoDetail',
        views: {
          'detail': {
            templateUrl: basePathArtesanias + 'artesanias.list.html'
          }
        }
      })
      .state('artesanoDetailReviews', {
        url: '/reviews',
        parent: 'artesanoDetail',
        views: {
          'detail': {
            templateUrl: basePathReviews + 'reviews.list.html'
          }
        }
      })
      .state('artesanoDetailFerias', {
        url: '/ferias',
        parent: 'artesanoDetail',
        views: {
          'detail': {
            templateUrl: basePathFerias + 'feria.list.html'
          }
        }
      });
  }]);
})(window.angular);