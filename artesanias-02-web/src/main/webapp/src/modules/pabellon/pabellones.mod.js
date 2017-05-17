(function (ng) {
  var mod = ng.module('pabellonModule', ['ui.router']);

  mod.constant('pabellonesContext', 'api/pabellones');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/pabellon/';
      var basePathStands = 'src/modules/stand/';
      var basePathSalones = 'src/modules/salon/';

      $urlRouterProvider.otherwise('/pabellones/list');

      $stateProvider
        .state('pabellones', {
          url: '/pabellones',
          abstract: true,
          resolve: {
            pabellones: ['$http', 'pabellonesContext',
              function ($http, pabellonesContext) {
                return $http.get(pabellonesContext);
              }]
          },
          views: {
            'mainView': {
              templateUrl: basePath + 'pabellones.html',
              controller: ['$scope', 'pabellones',
                function ($scope, pabellones) {
                  $scope.pabellonesRecords = pabellones.data;
                }]
            }
          }
        })
        .state('pabellonesList', {
          url: '/list',
          parent: 'pabellones',
          views: {
            'listView': {
              templateUrl: basePath + 'pabellones.list.html',
              controller: ['$http', 'pabellonesContext',
                function ($http, pabellonesContext) {

                  function add (id, pabellon) {
                    $http.post(pabellonesContext + '/' + id, pabellon);
                  }

                  function del (id) {
                    $http.delete(pabellonesContext + '/' + id);
                  }

                  //TODO
                  function crea () {
                    var tipo = document.getElementById('tipo').innerHTML;
                    var capacidad = document.getElementById('capacidad').innerHTML;

                    var nuevoPabellon = {
                      tipo: tipo,
                      capacidad: capacidad
                    };

                    $http.post(pabellonesContext, nuevoPabellon);

                  }
                }]
            }
          }
        })
        .state('pabelloneDetail', {
          url: '/{pabellonId:int}/detail',
          parent: 'pabellonesList',
          param: {
            pabellonId: null
          },
          resolve: {
            currentPabellon: ['$http', 'pabellonesContext', '$stateParams',
              function ($http, pabellonesContext, $params) {
                return $http.get(pabellonesContext + '/' + $params.pabellonId);
              }]
          },
          views: {
            'detailView': {
              templateUrl: basePath + 'pabellones.detail.html',
              controller: ['$scope', '$stateParams', 'currentPabellon',
                function ($scope, $params, currentPabellon) {
                  $scope.currentPabellon = currentPabellon.data;
                  $scope.standsRecords = $scope.currentPabellon.stands;
                  $scope.salonesRecords = $scope.currenPabellon.salones;
                }]
            }
          }
        })
        .state('pabellonDetailStands', {
          url: '/stands',
          parent: 'pabellonDetail',
          views: {
            'detail': {
              templateUrl: basePathStands + 'stands.list.html'
            }
          }
        })
        .state('pabellonDetailSalones', {
          url: '/salones',
          parent: 'pabellonDetail',
          views: {
            'detail': {
              templateUrl: basePathSalones + 'salones.list.html'
            }
          }
        });
    }]);
})(window.angular);
