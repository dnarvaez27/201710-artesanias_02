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
                param: {
                    context: null,
                    idParent: null
                },
                resolve: {
                    ferias: ['$http', '$stateParams', function ($http, $params) {
                            if (typeof $params.context === "undefined" || $params.context === null)
                                return $http.get('api/ferias');
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
                    idFeria: 0
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
                            }]
                    },
                    'listView': {
                        templateUrl: baseConferenciaPath + 'conferencias.list.html',
                        controller: ['$scope', 'currentFeria', function ($scope, currentFeria) {
                                $scope.conferenciaRecords = currentFeria.data.conferencias;
                            }]
                    }
                }
            });
        }]);
})(window.angular);