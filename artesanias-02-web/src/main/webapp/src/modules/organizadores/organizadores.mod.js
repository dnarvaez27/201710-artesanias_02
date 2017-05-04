(function (ng) {
    var mod = ng.module("organizadorModule", ['ui.router']);
    mod.constant("organizadorContext", "api/organizadores");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/organizadores/';
            var baseFeriaPath = 'src/modules/feria/';
            $urlRouterProvider.otherwise("/organizadores/List");

            $stateProvider.state('organizadores', {
                url: '/organizadores',
                abstract: true,
                
                views: {
                    'mainView': {
                        templateUrl: basePath + 'organizadores.html',
                
                       
                    }
                }
            }).state('organizadoresList', {
                url: '/list',
                parent: 'organizadores',
                resolve: {
                    organizadores: ['$http', 'organizadorContext', function ($http, organizadorContext) {
                            return $http.get(organizadorContext);
                        }]
                },
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
                parent: 'organizadoresList',
                param: {
                    organizadorId: null
                },
                
                resolve:  {
                    currentOrganizador: ['$http', 'organizadorContext', '$stateParams', function ($http, organizadorContext, $params) {
                            return $http.get(organizadorContext+'/'+$params.organizadorId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'organizadores.detail.html',
                        controller: ['$scope', 'currentOrganizador', function ($scope,  currentOrganizador) {
                                $scope.currentOrganizador = currentOrganizador.data;
                            }]
//                    },
//                    'listView': {
//                        templateUrl: baseFeriaPath + 'feria.list.html',
//                        controller: ['$scope', 'currentOrganizador', function ($scope, currentOrganizador) {
//                                $scope.currentFeria = currentOrganizador.data.ferias;
//                            }]
                    }
                }
            });
        }]);

              
})(window.angular);
