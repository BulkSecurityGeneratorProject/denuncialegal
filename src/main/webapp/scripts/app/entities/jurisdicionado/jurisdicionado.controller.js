'use strict';

angular.module('denuncialegalApp')
    .controller('JurisdicionadoController', function ($scope, Jurisdicionado) {
        $scope.jurisdicionados = [];
        $scope.loadAll = function() {
            Jurisdicionado.query(function(result) {
               $scope.jurisdicionados = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Jurisdicionado.save($scope.jurisdicionado,
                function () {
                    $scope.loadAll();
                    $('#saveJurisdicionadoModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.jurisdicionado = Jurisdicionado.get({id: id});
            $('#saveJurisdicionadoModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.jurisdicionado = Jurisdicionado.get({id: id});
            $('#deleteJurisdicionadoConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Jurisdicionado.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteJurisdicionadoConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.jurisdicionado = {nome: null, tipoJurisdicionado: null, id: null};
        };
    });
