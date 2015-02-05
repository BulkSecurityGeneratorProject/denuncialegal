'use strict';

angular.module('denuncialegalApp')
    .controller('ClienteDetailController', function ($scope, $stateParams, Cliente, Pessoa) {
        $scope.cliente = {};
        $scope.load = function (id) {
            Cliente.get({id: id}, function(result) {
              $scope.cliente = result;
            });
        };
        $scope.load($stateParams.id);
    });
