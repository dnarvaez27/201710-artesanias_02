(function (ng) {
    var mod = ng.module('feriaModule', ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/feria/';
            var baseConferenciaPath = 'src/modules/conferencia/';
            $urlRouterProvider.otherwise('/ferias/list');
            $stateProvider
            .state('ferias', {
                url: '/ferias',
                abstract: true,
                params: {
                    context: null,
                    idParent: null
                },
                resolve: {
                    ferias: ['$http', '$stateParams', function ($http, $params) {
                            if (typeof $params.context === "undefined" || $params.context === null || typeof $params.idParent === "undefined" || $params.idParent === null)
                                return $http.get('api/ferias');
                            if (typeof $params.context === "object")
                                return $http.get($params.context[0] + '/' + $params.idParent + '/ferias/' + $params.context[1]);
                            return $http.get($params.context + '/' + $params.idParent + '/ferias');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ferias.html',
                        controller: ['$scope', 'ferias', function ($scope, ferias) {
                                $scope.feriasRecords = ferias.data;
                            }]
                    }
                }
            })
            .state('feriasList', {
                url: '/list',
                parent: 'ferias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'feria.list.html'
                    }
                }
            })
            .state('feriaDetail', {
                url: '/{idFeria:int}/detail',
                parent: 'ferias',
                param: {
                    idFeria: null
                },
                resolve: {
                    currentFeria: ['$http', '$stateParams', function ($http, $params) {
                            return $http.get('api/ferias/' + $params.idFeria);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'feria.detail.html',
                        controller: ['$scope', 'currentFeria', function ($scope, currentFeria) {
                                $scope.currentFeria = currentFeria.data;
                                $scope.boletasRecords = currentFeria.data.boletas;
                                $scope.conferenciaRecords = currentFeria.data.conferencias;
                            }]
                    }
                }
            })
            .state('feriaBoletaList', {
                url: '/boletas',
                parent: 'feriaDetail',
                views: {
                  'detail': {
                    templateUrl: 'src/modules/boleta/boleta.list.html'
                  }
                }
            })
            .state('feriaConferenciaList', {
                url: '/conferencias',
                parent: 'feriaDetail',
                views: {
                  'detail': {
                    templateUrl: 'src/modules/conferencia/conferencias.list.html'
                  }
                }
            });
        }]);
})(window.angular);