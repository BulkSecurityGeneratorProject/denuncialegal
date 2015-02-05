'use strict';

angular.module('denuncialegalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('joao', {
                parent: 'entity',
                url: '/joao',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/joao/joaos.html',
                        controller: 'JoaoController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('joao');
                        return $translate.refresh();
                    }]
                }
            })
            .state('joaoDetail', {
                parent: 'entity',
                url: '/joao/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/joao/joao-detail.html',
                        controller: 'JoaoDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('joao');
                        return $translate.refresh();
                    }]
                }
            });
    });
