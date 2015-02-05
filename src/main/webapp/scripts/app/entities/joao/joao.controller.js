'use strict';

angular.module('denuncialegalApp')
    .controller('JoaoController', function ($scope, Joao, Pessoa) {
        $scope.joaos = [];
        $scope.pessoas = Pessoa.query();
        $scope.loadAll = function() {
            Joao.query(function(result) {
               $scope.joaos = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Joao.save($scope.joao,
                function () {
                    $scope.loadAll();
                    $('#saveJoaoModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.joao = Joao.get({id: id});
            $('#saveJoaoModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.joao = Joao.get({id: id});
            $('#deleteJoaoConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Joao.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteJoaoConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.joao = {idade: null, sexualidade: null, id: null};
        };
    });
