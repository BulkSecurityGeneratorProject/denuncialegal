'use strict';

angular.module('denuncialegalApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


