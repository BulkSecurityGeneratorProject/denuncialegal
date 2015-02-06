'use strict';

angular.module('denuncialegalApp')
    .controller('CategoriaIrregularidadesDetailController', function ($scope, $stateParams, CategoriaIrregularidades) {
        $scope.categoriaIrregularidades = {};
        $scope.load = function (id) {
            CategoriaIrregularidades.get({id: id}, function(result) {
              $scope.categoriaIrregularidades = result;
            });
        };
        $scope.load($stateParams.id);
    });
