(function (ng) {
    var app = ng.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies
        'feriaModule',
        'artesanoModule',
        'espectadorModule',
        'ciudadModule',
        'organizadorModule',
        'salonModule',
        'conferenciaModule',
        'espacioModule',
        'boletaModule',
        'dashboardModule'

    ]);
    app.controller('mainController', function ($scope) {
        $scope.isActive = function (path) {
            return window.location.hash.substr(3, path.length) === path;
        };
    });
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);


