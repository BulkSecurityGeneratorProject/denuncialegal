'use strict';

angular.module('denuncialegalApp')
    .controller('PessoaController', function ($scope, Pessoa) {
        $scope.pessoas = [];
        $scope.loadAll = function() {
            Pessoa.query(function(result) {
               $scope.pessoas = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Pessoa.save($scope.pessoa,
                function () {
                    $scope.loadAll();
                    $('#savePessoaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.pessoa = Pessoa.get({id: id});
            $('#savePessoaModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.pessoa = Pessoa.get({id: id});
            $('#deletePessoaConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Pessoa.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePessoaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.pessoa = {nome: null, idade: null, id: null};
        };
    });
