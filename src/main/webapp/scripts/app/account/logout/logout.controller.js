'use strict';

angular.module('denuncialegalApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
