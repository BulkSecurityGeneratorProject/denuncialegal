'use strict';

angular.module('denuncialegalApp')
    .factory('Cliente', function ($resource) {
        return $resource('api/clientes/:id', {}, {
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
