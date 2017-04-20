(function (ng) {
    var mod = ng.module("organizadorModule", ['ui.router']);
    mod.constant("organizadorContext", "api/organizadores");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/organizadores/';
            var baseFeriaPath = 'src/modules/ferias/';
            $urlRouterProvider.otherwise("/organizadoresList");

            $stateProvider.state('organizadores', {
                url: '/organizadores',
                abstract: true,
                resolve: {
                    organizadores: ['$http', 'organizadoresContext', function ($http, organizadoresContext) {
                            return $http.get(organizadoresContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'organizadores.html',
                       
                    }
                }
            }).state('organizadoresList', {
                url: '/list',
                parent: 'organizadores',
                views: {
                    'listView': {
                        templateUrl: basePath + 'organizadores.list.html',
                         controller: ['$scope', 'organizadores', function ($scope, organizadores) {
                                $scope.organizadoresRecords = organizadores.data;
                            }]
                        
                    }
                }
            }).state('organizadorDetail', {
                url: '/{organizadorId:int}/detail',
                parent: 'organizadores',
                param: {
                    organizadorId: null
                },
                
                resolve:  {
                    currentOrganizador: ['$http', 'organizadoresContext', '$stateParams', function ($http, organizadoresContext, $params) {
                            return $http.get(organizadoresContext+'/'+$params.organizadorId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'organizadores.detail.html',
                        controller: ['$scope', 'currentOrganizador', function ($scope,  currentBook) {
                                $scope.currentOrganizador = $scope.currentOrganizador.data;
                            }]
                    },
                    'listView': {
                        templateUrl: baseFeriaPath + 'feira.list.html',
                        controller: ['$scope', 'currentFeria', function ($scope, currentFeria) {
                                $scope.currentFeria = currentFeria.data.books;
                            }]
                    }
                }
            });
        }]);

              
})(window.angular);
