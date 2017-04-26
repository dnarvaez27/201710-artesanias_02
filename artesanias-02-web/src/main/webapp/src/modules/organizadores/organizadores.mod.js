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
                        templateUrl: basePath + 'organizadores.list.html',
                        controller: ['$http', 'organizadoresContext',
                function ($http, organizadoresContext) {

                  function add (id, organizador) {
                    $http.post(organizadoresContext + '/' + id, organizador);
                  }

                  function del (id) {
                    $http.delete(organizadoresContext + '/' + id);
                  }

                  //TODO
                  function crea () {
                    var name = document.getElementById('nombre').innerHTML;
                    var ide = document.getElementById('identificacion').innerHTML;

                    var nuevoArtesano = {
                      nombre: name,
                      identificacion: ide
                    };

                    $http.post(organizadoresContext, nuevoArtesano);

                  }
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
                    currentOrganizador: ['$http', 'organizadoresContext', '$stateParams', function ($http, organizadoresContext, $params) {
                            return $http.get(organizadoresContext+'/'+$params.organizadorId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'organizadores.detail.html',
                        controller: ['$scope', 'currentOrganizador', function ($scope,  currentOrganizador) {
                                $scope.currentOrganizador = currentOrganizador.data;
                            }]
                    },
                    'listView': {
                        templateUrl: baseFeriaPath + 'feira.list.html',
                        controller: ['$scope', 'currentOrganizador', function ($scope, currentOrganizador) {
                                $scope.currentFeria = currentOrganizador.data.ferias;
                            }]
                    }
                }
            });
        }]);

              
})(window.angular);
