(function (ng) {
    var mod = ng.module('boletaModule', ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/boleta/';
            $urlRouterProvider.otherwise('/boletas/list');
            $stateProvider
            .state('boletas', {
                url: '/boletas',
                abstract: true,
                params: {
                    context: null,
                    idParent: null
                },
                resolve: {
                    boletas: ['$http', '$stateParams', function ($http, $params) {
                            return $http.get($params.context + '/' + $params.idParent + '/boletas');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'boletas.html',
                        controller: ['$scope', 'boletas', function ($scope, boletas) {
                                $scope.boletasRecords = boletas.data;
                            }]
                    }
                }
            })
            .state('boletasList', {
                url: '/list',
                parent: 'boletas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'boleta.list.html'
                    }
                }
            })
            .state('boletaDetail', {
                url: '/{idBoleta:int}/detail',
                parent: 'boletasList',
                param: {
                    idBoleta: null
                },
                resolve: {
                    currentBoleta: ['$http', '$stateParams', function ($http, $params) {
                            return $http.get($params.context + '/' + $params.idParent + '/boletas/' + $params.idBoleta);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'boleta.detail.html',
                        controller: ['$scope', 'currentBoleta', function ($scope, currentBoleta) {
                                $scope.currentBoleta = currentBoleta;
                            }]
                    }
                }
            });
        }]);
})(window.angular);