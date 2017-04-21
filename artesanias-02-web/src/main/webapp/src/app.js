(function (ng) {
  var app = angular.module('mainApp', [
    // External dependencies
    'ui.router',
    // Internal modules dependencies
    'artesanoModule',
    'espectadorModule',
    // 'pabellonModule'
    'organizadorModule',
    'salonModule',
    'conferenciaModule',
    'feriaModule',
    'espacioModule',
    'boletaModule'
  ]);
  // Resuelve problemas de las promesas
  app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
  }]);
})(window.angular);


