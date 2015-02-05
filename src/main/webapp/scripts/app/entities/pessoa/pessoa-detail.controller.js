'use strict';

angular.module('denuncialegalApp')
    .controller('PessoaDetailController', function ($scope, $stateParams, Pessoa) {
        $scope.pessoa = {};
        $scope.load = function (id) {
            Pessoa.get({id: id}, function(result) {
              $scope.pessoa = result;
            });
        };
        $scope.load($stateParams.id);
    });
