(function (ng) {
    var mod = ng.module('espacioModule', ['ui.router']);
    mod.constant("ciudadesContext", "api/ciudades");
    mod.constant("espaciosContext", "espacios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/espacio/';
            var baseFeriaPath = 'src/modules/feria/';
            $urlRouterProvider.otherwise('/espacios/list');
            $stateProvider
            .state('espacios', {
                url: '/espacios',
                abstract: true,
                parent: 'ciudadDetail',
                resolve: {
                    espacios: ['$http', 'ciudadesContext', 'espaciosContext', '$stateParams', function ($http, ciudadesContext, espaciosContext, $params) {
                            return $http.get(ciudadesContext + '/' + $params.ciudadId + '/' + espaciosContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'espacios.html',
                        controller: ['$scope', 'espacios', function ($scope, espacios) {
                                $scope.espaciosRecords = espacios.data;
                            }]
                    }
                }
            })
            .state('espaciosList', {
                url: '/list',
                parent: 'espacios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'espacio.list.html'
                    }
                }
            })
            .state('espacioDetail', {
                url: '/{idEspacio:int}/detail',
                parent: 'espacios',
                param: {
                    idEspacio: 0
                },
                resolve: {
                    currentEspacio: ['$http', 'ciudadesContext', 'espaciosContext', '$stateParams', function ($http, ciudadesContext, espaciosContext, $params) {
                            return $http.get(ciudadesContext + '/' + $params.idCiudad + '/' + espaciosContext + '/' + $params.idEspacio);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'espacio.detail.html',
                        controller: ['$scope', 'currentEspacio', function ($scope, currentEspacio) {
                                $scope.currentEspacio = currentEspacio.data;
                            }]
                    },
                    'listView': {
                        templateUrl: baseFeriaPath + 'ferias.list.html',
                        controller: ['$scope', 'currentEspacio', function ($scope, currentEspacio) {
                                $scope.feriaRecords = currentEspacio.data.ferias;
                            }]
                    }
                }
            });
        }]);
})(window.angular);