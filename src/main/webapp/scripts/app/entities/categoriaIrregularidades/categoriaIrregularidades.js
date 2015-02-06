'use strict';

angular.module('denuncialegalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('categoriaIrregularidades', {
                parent: 'entity',
                url: '/categoriaIrregularidades',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/categoriaIrregularidades/categoriaIrregularidadess.html',
                        controller: 'CategoriaIrregularidadesController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('categoriaIrregularidades');
                        return $translate.refresh();
                    }]
                }
            })
            .state('categoriaIrregularidadesDetail', {
                parent: 'entity',
                url: '/categoriaIrregularidades/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/categoriaIrregularidades/categoriaIrregularidades-detail.html',
                        controller: 'CategoriaIrregularidadesDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('categoriaIrregularidades');
                        return $translate.refresh();
                    }]
                }
            });
    });
