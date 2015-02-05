'use strict';

angular.module('denuncialegalApp')
    .controller('JurisdicionadoDetailController', function ($scope, $stateParams, Jurisdicionado) {
        $scope.jurisdicionado = {};
        $scope.load = function (id) {
            Jurisdicionado.get({id: id}, function(result) {
              $scope.jurisdicionado = result;
            });
        };
        $scope.load($stateParams.id);
    });
