'use strict';

angular.module('denuncialegalApp')
    .controller('DenunciaDetailController', function ($scope, $stateParams, Denuncia, Jurisdicionado) {
        $scope.denuncia = {};
        $scope.load = function (id) {
            Denuncia.get({id: id}, function(result) {
              $scope.denuncia = result;
            });
        };
        $scope.load($stateParams.id);
    });
