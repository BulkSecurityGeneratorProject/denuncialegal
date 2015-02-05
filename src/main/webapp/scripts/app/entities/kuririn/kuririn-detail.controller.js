'use strict';

angular.module('denuncialegalApp')
    .controller('KuririnDetailController', function ($scope, $stateParams, Kuririn, Pessoa) {
        $scope.kuririn = {};
        $scope.load = function (id) {
            Kuririn.get({id: id}, function(result) {
              $scope.kuririn = result;
            });
        };
        $scope.load($stateParams.id);
    });
