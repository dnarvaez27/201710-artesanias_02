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
  // Resuelve problemas de las promesas
  app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
  }]);
})(window.angular);


