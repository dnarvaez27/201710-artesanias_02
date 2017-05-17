(function (ng) {
    var mod = ng.module("standModule", ['ui.router']);
    mod.constant("standsContext", "api/stands");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/stands/';
            $urlRouterProvider.otherwise("/standsList");

            $stateProvider.state('stands', {
                url: '/stands',
                abstract: true,
                resolve: {
                    stands: ['$http', 'standsContext', function ($http, standsContext) {
                            return $http.get(standsContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'stands.html',
                        controller: ['$scope', 'stands', function ($scope, stands) {
                                $scope.standsRecords = stands.data;
                            }]
                    }
                }
            }).state('standsList', {
                url: '/list',
                parent: 'stands',
                views: {
                    'listView': {
                        templateUrl: basePath + 'stands.list.html'
                    }
                }
            }).state('standDetail', {
                url: '/{standId:int}/detail',
                parent: 'stands',
                param: {
                    standId: null
                },
                resolve:  {
                    currentStand: ['$http', 'standsContext', '$stateParams', function ($http, standsContext, $params) {
                            return $http.get(standsContext+'/'+$params.standId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'stands.detail.html',
                        controller: ['$scope', 'currentStand', function ($scope,  currentStand) {
                                $scope.currentStand = currentStand.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
