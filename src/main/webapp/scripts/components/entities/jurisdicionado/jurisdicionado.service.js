'use strict';

angular.module('denuncialegalApp')
    .factory('Jurisdicionado', function ($resource) {
        return $resource('api/jurisdicionados/:id', {}, {
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
