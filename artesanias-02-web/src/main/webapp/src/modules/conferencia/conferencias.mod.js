(function (ng) {
    var mod = ng.module("conferenciaModule", ['ui.router']);
    mod.constant("conferneciasContext", "api/confernecias");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/confernecias/';
            $urlRouterProvider.otherwise("/conferneciasList");

            $stateProvider.state('confernecias', {
                url: '/confernecias',
                abstract: true,
                parent: 'feriaDetail',
                resolve: {
                    conferencias: ['$http', 'conferneciasContext', function ($http, conferneciasContext) {
                            return $http.get(conferneciasContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'confernecias.html',
                        
                    }
                }
            }).state('conferneciasList', {
                url: '/list',
                parent: 'confernecias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'confernecias.list.html',
                        controller: ['$scope', 'confernecias', function ($scope, confernecias) {
                                $scope.confereciasRecords = confernecias.data;
                            }]
                    }
                }
            }).state('conferneciaDetail', {
                url: '/{conferneciaId:int}/detail',
                parent: 'confernecia',
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
                        templateUrl: basePath + 'confernecias.detail.html',
                        controller: ['$scope', 'currentConferencia', function ($scope,  currentConferencia) {
                                $scope.currentConferencia = currentConferencia.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
