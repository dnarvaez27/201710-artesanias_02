(function (ng) {
  var mod = ng.module('pabellonModule', ['ui.router'])
  mod.constant('pabellonesContext', 'api/pabellones')
  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/pabellon/';
    var baseStandPath = 'src/modules/stand/';
    $urlRouterProvider.otherwise('/pabellonesList')

    $stateProvider.state('pabellones', {
                url: '/pabellones',
                abstract: true,
                resolve: {
                    pabellones: ['$http', 'pabellonesContext', function ($http, pabellonesContext) {
                            return $http.get(pabellonesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pabellones.html'
                    }
                }
            }).state('pabellonesList', {
                url: '/list',
                parent: 'pabellones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pabellones.list.html',
                        controller: ['$scope', 'pabellones', function ($scope, pabellones) {
                                $scope.pabellonesRecords = pabellones.data;

                            }]
                    }
                }
            }).state('pabellonDetail', {
                url: '/{pabellonId:int}/detail',
                parent: 'pabellones',
                param: {
                    pabellonId: null
                },
                resolve: {
                    currentPabellon: ['$http', 'pabellonesContext', '$stateParams', function ($http, pabellonesContext, $params) {
                            return $http.get(pabellonesContext + '/' + $params.pabellonId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'pabellones.detail.html',
                        controller: ['$scope', 'currentPabellon', function ($scope, currentPabellon) {
                                $scope.currentPabellon = currentPabellon.data;                               
                            }]
                    },
                    'listView': {
                        templateUrl: baseStandPath + 'stands.list.html',
                        controller: ['$scope', 'currentPabellon', function ($scope, currentPabellon) {
                                $scope.standsRecords = currentPabellon.data.stands;
                            }]
                    }
                }
            });
        }]);
})(window.angular);