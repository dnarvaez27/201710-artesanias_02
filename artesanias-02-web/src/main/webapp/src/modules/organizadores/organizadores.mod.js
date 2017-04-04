(function (ng) {
    var mod = ng.module("organizadorModule", ['ui.router']);
    mod.constant("organizadorContext", "api/organizadores");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/organizadores/';
            $urlRouterProvider.otherwise("/organizadoresList");

            $stateProvider.state('organizadores', {
                url: '/organizadores',
                abstract: true,
                resolve: {
                    books: ['$http', 'organizadoresContext', function ($http, organizadoresContext) {
                            return $http.get(organizadoresContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'organizadores.html',
                        controller: ['$scope', 'organizadores', function ($scope, organizadores) {
                                $scope.organizadoresRecords = organizadores.data;
                            }]
                    }
                }
            }).state('organizadoresList', {
                url: '/list',
                parent: 'organizadores',
                views: {
                    'listView': {
                        templateUrl: basePath + 'organizadores.list.html'
                    }
                }
            }).state('organizadorDetail', {
                url: '/{organizadorId:int}/detail',
                parent: 'organizadores',
                param: {
                    organizadorId: null
                },
                
                resolve:  {
                    currentBook: ['$http', 'organizadoresContext', '$stateParams', function ($http, organizadoresContext, $params) {
                            return $http.get(organizadoresContext+'/'+$params.bookId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'organizadores.detail.html',
                        controller: ['$scope', 'currentOrganizador', function ($scope,  currentBook) {
                                $scope.currentOrganizador = $scope.currentOrganizador[$params.organizadorId - 1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);
