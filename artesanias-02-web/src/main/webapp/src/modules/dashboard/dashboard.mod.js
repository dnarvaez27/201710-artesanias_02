(function (ng) {
  var mod = ng.module('dashboardModule', ['ui.router']);
  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/dashboard/';
            $urlRouterProvider.otherwise('/');
            $stateProvider
            .state('/', {
                url: '/',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'dashboard.html'
                    }
                }
            })}]);
})(window.angular);