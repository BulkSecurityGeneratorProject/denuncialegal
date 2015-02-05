'use strict';

angular.module('denuncialegalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('pessoa', {
                parent: 'entity',
                url: '/pessoa',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pessoa/pessoas.html',
                        controller: 'PessoaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pessoa');
                        return $translate.refresh();
                    }]
                }
            })
            .state('pessoaDetail', {
                parent: 'entity',
                url: '/pessoa/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pessoa/pessoa-detail.html',
                        controller: 'PessoaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pessoa');
                        return $translate.refresh();
                    }]
                }
            });
    });
