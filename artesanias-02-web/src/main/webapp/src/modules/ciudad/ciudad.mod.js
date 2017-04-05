(function (ng) {
    var mod = ng.module("ciudadModule", ['ui.router']);
    mod.constant("ciudadesContext", "api/ciudad");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ciudad/';
            $urlRouterProvider.otherwise("/ciudadList");

            $stateProvider.state('ciudades', {
                url: '/ciudades',
                abstract: true,
                resolve: {
                    ciudades: ['$http', 'ciudadContext', function ($http, ciudadContext) {
                            return $http.get(ciudadContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ciudades.html',
                        controller: ['$scope', 'ciudades', function ($scope, ciudades) {
                                $scope.ciudadRecords = ciudad.data;
                            }]
                    }
                }
            }).state('ciudadList', {
                url: '/list',
                parent: 'ciudades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ciudades.list.html'
                    }
                }
            }).state('ciudadDetail', {
                url: '/{ciudadId:int}/detail',
                parent: 'ciudades',
                param: {
                    ciudadId: null
                },
                resolve:  {
                    currentCiudad: ['$http', 'ciudadContext', '$stateParams', function ($http, ciudadContext, $params) {
                            return $http.get(ciudadContext+'/'+$params.ciudadId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ciudades.detail.html',
                        controller: ['$scope', 'currentCiudad', function ($scope,  currentCiudad) {
                                $scope.currentCiudad = currentCiudad.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
