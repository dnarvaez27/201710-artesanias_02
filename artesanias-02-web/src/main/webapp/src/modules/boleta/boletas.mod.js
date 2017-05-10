(function (ng) {
    var mod = ng.module('boletaModule', ['ui.router']);
    mod.controller('boletaController', function ($scope) {
        $scope.darPrecio = function (boleta) {
            return boleta.tipo_boleta === 0 ? boleta.precio*boleta.feria.descuentoMenores : 
                    boleta.tipo_boleta === 1 ? boleta.precio*boleta.feria.descuentoRegular :
                    boleta.precio*boleta.feria.descuentoMayores;
        };
    });
})(window.angular);