'use strict';

/* Controllers */

angular.module('myApp.controllers', [])

    .controller('NavController', ['$scope', function ($scope) {
        $scope.debug = 'nav';
        $scope.languages = ['deutsch', 'englisch'];
        $scope.selectedLanguage = 'deutsch';
    }])

;