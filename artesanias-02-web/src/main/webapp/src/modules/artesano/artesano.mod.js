(function (ng) {
  var mod = ng.module('artesanoModule', ['ui.router']);
  mod.constant('artesanosContext', 'api/artesanos');
  mod.config([
    '$stateProvider',
    '$urlRouterProvder',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/artesanos/';
      $urlRouterProvider.otherwise('/artesanosList');

      $stateProvider.state('artesanos', {
        url: '/artesanos',
        abstract: true,
        resolve: {
          artesanos: ['$http', function ($http) {
            return $http.get('data/artesanos.json'); // TODO
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
          url: 'list/',
          parent: 'artesanos',
          views: {
            'listView': {templateUrl: basePath + 'artesanos.list.html'}
          }
        })
        .state('artesanosDetail', {
          url: '/{artesanoId:int}/detail',
          parent: 'artesanos',
          params: {artesanoId: null},
          views: {
            'listView': {templateUrl: basePath + 'artesanos.list.html'},
            'detailView': {
              templateUrl: basePath + 'artesanos.detail.html',
              controller: ['$scope', '$stateParams', function ($scope, $stateParams) {
                $scope.currentArtesano = $scope.artesanosRecords[$stateParams.artesanoId - 1];
              }]
            }
          }
        });
    }
  ]);
})(window.angular);