'use strict';

angular.module('denuncialegalApp')
    .controller('JoaoDetailController', function ($scope, $stateParams, Joao, Pessoa) {
        $scope.joao = {};
        $scope.load = function (id) {
            Joao.get({id: id}, function(result) {
              $scope.joao = result;
            });
        };
        $scope.load($stateParams.id);
    });
