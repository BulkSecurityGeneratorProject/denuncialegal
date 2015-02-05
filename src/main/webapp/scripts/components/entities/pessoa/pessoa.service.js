'use strict';

angular.module('denuncialegalApp')
    .factory('Pessoa', function ($resource) {
        return $resource('api/pessoas/:id', {}, {
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
