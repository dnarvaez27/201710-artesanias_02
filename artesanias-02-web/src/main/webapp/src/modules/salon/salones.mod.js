(function (ng) {
    var mod = ng.module("salonModule", ['ui.router']);
    mod.constant("salonesContext", "api/salones");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/salones/';
            $urlRouterProvider.otherwise("/salonesList");

            $stateProvider.state('salones', {
                url: '/salones',
                abstract: true,
                resolve: {
                    stands: ['$http', 'salonesContext', function ($http, salonesContext) {
                            return $http.get(salonesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'salones.html',
                        controller: ['$scope', 'salones', function ($scope, salones) {
                                $scope.standsRecords = salones.data;
                            }]
                    }
                }
            }).state('salonesList', {
                url: '/list',
                parent: 'salones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'salones.list.html'
                    }
                }
            }).state('salonDetail', {
                url: '/{salonId:int}/detail',
                parent: 'salones',
                param: {
                    standId: null
                },
                resolve:  {
                    currentStand: ['$http', 'salonesContext', '$stateParams', function ($http, salonesContext, $params) {
                            return $http.get(salonesContext+'/'+$params.salonId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'salones.detail.html',
                        controller: ['$scope', 'currentSalon', function ($scope,  currentSalon) {
                                $scope.currentStand = currentSalon.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
