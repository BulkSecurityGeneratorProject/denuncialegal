'use strict';

angular.module('denuncialegalApp')
    .controller('CategoriaIrregularidadesController', function ($scope, CategoriaIrregularidades) {
        $scope.categoriaIrregularidadess = [];
        $scope.loadAll = function() {
            CategoriaIrregularidades.query(function(result) {
               $scope.categoriaIrregularidadess = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            CategoriaIrregularidades.save($scope.categoriaIrregularidades,
                function () {
                    $scope.loadAll();
                    $('#saveCategoriaIrregularidadesModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.categoriaIrregularidades = CategoriaIrregularidades.get({id: id});
            $('#saveCategoriaIrregularidadesModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.categoriaIrregularidades = CategoriaIrregularidades.get({id: id});
            $('#deleteCategoriaIrregularidadesConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            CategoriaIrregularidades.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteCategoriaIrregularidadesConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.categoriaIrregularidades = {nome: null, id: null};
        };
    });
