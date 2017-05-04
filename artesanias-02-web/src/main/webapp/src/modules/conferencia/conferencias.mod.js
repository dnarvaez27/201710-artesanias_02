(function (ng) {
    var mod = ng.module("conferenciaModule", ['ui.router']);
    mod.constant("conferneciasContext", "api/confernecias");
    mod.constant();
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/confernecias/';
            $urlRouterProvider.otherwise("/conferencias/list");

            $stateProvider.state('conferencias', {
                url: '/conferencias',
                abstract: true,
                parent: 'feriaDetail',
                resolve: {
                    conferencias: ['$http', 'conferneciasContext', function ($http, conferneciasContext) {
                            return $http.get(conferneciasContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'conferencias.html',
                        
                    }
                }
            }).state('conferenciasList', {
                url: '/list',
                parent: 'conferencias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'confernecias.list.html',
                        controller: ['$scope', 'confernecias', function ($scope, confernecias) {
                                $scope.confereciasRecords = confernecias.data;
                            }]
                    }
                }
            }).state('conferenciaDetail', {
                url: '/{conferneciaId:int}/detail',
                parent: 'conferencias',
                param: {
                    conferenciaId: null
                },
                resolve:  {
                    currentConferencia: ['$http', 'conferneciasContext', '$stateParams', function ($http, conferneciasContext, $params) {
                            return $http.get(conferneciasContext+'/'+$params.conferneciaId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'conferencias.detail.html',
                        controller: ['$scope', 'currentConferencia', function ($scope,  currentConferencia) {
                                $scope.currentConferencia = currentConferencia.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
