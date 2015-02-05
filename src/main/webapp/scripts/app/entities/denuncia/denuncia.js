'use strict';

angular.module('denuncialegalApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('denuncia', {
                parent: 'entity',
                url: '/denuncia',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/denuncia/denuncias.html',
                        controller: 'DenunciaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('denuncia');
                        return $translate.refresh();
                    }]
                }
            })
            .state('denunciaDetail', {
                parent: 'entity',
                url: '/denuncia/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/denuncia/denuncia-detail.html',
                        controller: 'DenunciaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('denuncia');
                        return $translate.refresh();
                    }]
                }
            });
    });
