'use strict';

angular.module('denuncialegalApp')
    .factory('Denuncia', function ($resource) {
        return $resource('api/denuncias/:id', {}, {
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
