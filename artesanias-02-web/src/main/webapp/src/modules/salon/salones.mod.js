(function (ng) {
    var mod = ng.module("salonModule", ['ui.router']);
    mod.constant("salonesContext", "api/salones");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/salones/';
            $urlRouterProvider.otherwise("/salonesList");

            $stateProvider.state('salones', {
                url: '/salones',
                abstract: true,
                parent:'pabellonDetail',
                resolve: {
                    salones: ['$http', 'salonesContext', function ($http, salonesContext) {
                            return $http.get(salonesContext);
                        }]
                },
                views: {
                    'childrenVIew': {
                        templateUrl: basePath + 'salones.html',
                        
                    }
                }
            }).state('salonesList', {
                url: '/list',
                parent: 'salones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'salones.list.html',
                        controller: ['$scope', 'salones', function ($scope, salones) {
                                $scope.salonesRecords = salones.data;
                            }]
                    }
                }
            }).state('salonDetail', {
                url: '/{salonId:int}/detail',
                parent: 'salones',
                param: {
                    standId: null
                },
                resolve:  {
                    currentSalon: ['$http', 'salonesContext', '$stateParams', function ($http, salonesContext, $params) {
                            return $http.get(salonesContext+'/'+$params.salonId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'salones.detail.html',
                        controller: ['$scope', 'currentSalon', function ($scope,  currentSalon) {
                                $scope.currentSalon = currentSalon.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
