'use strict';

angular.module('denuncialegalApp')
    .controller('DenunciaController', function ($scope, Denuncia, Jurisdicionado) {
        $scope.denuncias = [];
        $scope.jurisdicionados = Jurisdicionado.query();
        $scope.loadAll = function() {
            Denuncia.query(function(result) {
               $scope.denuncias = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Denuncia.save($scope.denuncia,
                function () {
                    $scope.loadAll();
                    $('#saveDenunciaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.denuncia = Denuncia.get({id: id});
            $('#saveDenunciaModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.denuncia = Denuncia.get({id: id});
            $('#deleteDenunciaConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Denuncia.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteDenunciaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.denuncia = {titulo: null, descricao: null, id: null};
        };
    });
