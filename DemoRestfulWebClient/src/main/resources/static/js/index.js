(function () {
    'use strict';
    angular.module('indexApp', [
        'ngMaterial',
        'ngRoute',
        'ngMessages'
    ])
    /* config section */
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/create', {templateUrl: '/html/partials/create.html'})
            .when('/read', {templateUrl: '/html/partials/read.html'})
            .when('/update', {templateUrl: '/html/partials/update.html'})
            .when('/delete', {templateUrl: '/html/partials/delete.html'})
            .when('/search', {templateUrl: '/html/partials/search.html'})
            .when('/help', {templateUrl: '/html/partials/help.html'})
            .otherwise({redirectTo: '/help'});
    }])
    /* directives section */
    .directive('pmHeading', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: '/html/elements/pm-heading.html'
        }
    })
    .directive('pmSubheading', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: '/html/elements/pm-subheading.html'
        }
    })
    .directive('pmFabToolbar', function() {
        return {
            restrict: 'E',
            templateUrl: '/html/elements/pm-fab-toolbar.html'
        }
    })
    /* controllers section */
    .controller('helpCtrl', ['$scope', '$http', function($scope, $http) {
        $scope.method = 'GET';
        $scope.url = '';
    }])
    .controller('indexCtrl', ['$scope', function($scope) {

    }]);
})();
