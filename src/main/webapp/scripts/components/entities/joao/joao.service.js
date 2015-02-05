'use strict';

angular.module('denuncialegalApp')
    .factory('Joao', function ($resource) {
        return $resource('api/joaos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
