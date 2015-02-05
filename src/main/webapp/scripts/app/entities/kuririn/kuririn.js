'use strict';

angular.module('denuncialegalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('kuririn', {
                parent: 'entity',
                url: '/kuririn',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/kuririn/kuririns.html',
                        controller: 'KuririnController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('kuririn');
                        return $translate.refresh();
                    }]
                }
            })
            .state('kuririnDetail', {
                parent: 'entity',
                url: '/kuririn/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/kuririn/kuririn-detail.html',
                        controller: 'KuririnDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('kuririn');
                        return $translate.refresh();
                    }]
                }
            });
    });
