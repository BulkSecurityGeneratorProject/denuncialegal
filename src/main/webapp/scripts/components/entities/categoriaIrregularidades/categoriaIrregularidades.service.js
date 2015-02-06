'use strict';

angular.module('denuncialegalApp')
    .factory('CategoriaIrregularidades', function ($resource) {
        return $resource('api/categoriaIrregularidadess/:id', {}, {
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
