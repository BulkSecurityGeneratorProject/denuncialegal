'use strict';

angular.module('denuncialegalApp')
    .controller('KuririnController', function ($scope, Kuririn, Pessoa) {
        $scope.kuririns = [];
        $scope.pessoas = Pessoa.query();
        $scope.loadAll = function() {
            Kuririn.query(function(result) {
               $scope.kuririns = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Kuririn.save($scope.kuririn,
                function () {
                    $scope.loadAll();
                    $('#saveKuririnModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.kuririn = Kuririn.get({id: id});
            $('#saveKuririnModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.kuririn = Kuririn.get({id: id});
            $('#deleteKuririnConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Kuririn.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteKuririnConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.kuririn = {morresempre: null, id: null};
        };
    });
