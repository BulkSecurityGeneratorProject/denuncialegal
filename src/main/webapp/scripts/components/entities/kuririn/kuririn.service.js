'use strict';

angular.module('denuncialegalApp')
    .factory('Kuririn', function ($resource) {
        return $resource('api/kuririns/:id', {}, {
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
