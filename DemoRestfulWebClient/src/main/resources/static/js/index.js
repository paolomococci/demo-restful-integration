(function () {
    'use strict';
    angular.module('indexApp', [
        'ngMaterial',
        'ngRoute',
        'ngMessages'
    ])
    .directive('pmHeading', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: '/html/elements/pm-heading.html'
        }
    })
    .directive('pmFabToolbar', function() {
        return {
            restrict: 'E',
            templateUrl: '/html/elements/pm-fab-toolbar.html'
        }
    })
    .controller('indexCtrl', ['$scope', function($scope) {
        
    }]);
})();
