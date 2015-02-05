'use strict';

angular.module('denuncialegalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('jurisdicionado', {
                parent: 'entity',
                url: '/jurisdicionado',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jurisdicionado/jurisdicionados.html',
                        controller: 'JurisdicionadoController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('jurisdicionado');
                        return $translate.refresh();
                    }]
                }
            })
            .state('jurisdicionadoDetail', {
                parent: 'entity',
                url: '/jurisdicionado/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jurisdicionado/jurisdicionado-detail.html',
                        controller: 'JurisdicionadoDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('jurisdicionado');
                        return $translate.refresh();
                    }]
                }
            });
    });
