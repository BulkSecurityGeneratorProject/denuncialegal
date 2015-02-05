'use strict';

angular.module('denuncialegalApp')
    .controller('ClienteController', function ($scope, Cliente, Pessoa) {
        $scope.clientes = [];
        $scope.pessoas = Pessoa.query();
        $scope.loadAll = function() {
            Cliente.query(function(result) {
               $scope.clientes = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Cliente.save($scope.cliente,
                function () {
                    $scope.loadAll();
                    $('#saveClienteModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.cliente = Cliente.get({id: id});
            $('#saveClienteModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.cliente = Cliente.get({id: id});
            $('#deleteClienteConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Cliente.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteClienteConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.cliente = {cpf: null, id: null};
        };
    });
