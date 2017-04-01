(function (ng) {
  var app = angular.module('mainApp', [
    // Dependencias Externas
    'ui.router',
    // Dependencias de Modulos Internos
  ]);
  // Resuelve problemas de las promesas
  app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
  }]);
})(window.angular);