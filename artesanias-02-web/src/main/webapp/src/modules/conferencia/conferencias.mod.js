(function (ng) {
    var mod = ng.module("conferneciaModule", ['ui.router']);
    mod.constant("conferneciasContext", "api/confernecias");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/confernecias/';
            $urlRouterProvider.otherwise("/conferneciasList");

            $stateProvider.state('confernecias', {
                url: '/confernecias',
                abstract: true,
                resolve: {
                    stands: ['$http', 'conferneciasContext', function ($http, conferneciasContext) {
                            return $http.get(conferneciasContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'confernecias.html',
                        controller: ['$scope', 'confernecias', function ($scope, confernecias) {
                                $scope.standsRecords = confernecias.data;
                            }]
                    }
                }
            }).state('conferneciasList', {
                url: '/list',
                parent: 'confernecias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'confernecias.list.html'
                    }
                }
            }).state('conferneciaDetail', {
                url: '/{conferneciaId:int}/detail',
                parent: 'confernecia',
                param: {
                    standId: null
                },
                resolve:  {
                    currentStand: ['$http', 'conferneciasContext', '$stateParams', function ($http, conferneciasContext, $params) {
                            return $http.get(conferneciasContext+'/'+$params.conferneciaId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'confernecias.detail.html',
                        controller: ['$scope', 'currentSalon', function ($scope,  currentConferencia) {
                                $scope.currentConferencia = currentConferencia.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
