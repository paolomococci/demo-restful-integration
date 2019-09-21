(function () {
    'use strict';
    angular.module('indexApp', [
        'ngMaterial',
        'ngRoute',
        'ngMessages'
    ])
    /* config section */
    .config(['$routeProvider', '$sceDelegateProvider', function($routeProvider, $sceDelegateProvider) {
        $routeProvider
            .when('/create', {templateUrl: '/html/partials/create.html'})
            .when('/read', {templateUrl: '/html/partials/read.html'})
            .when('/update', {templateUrl: '/html/partials/update.html'})
            .when('/delete', {templateUrl: '/html/partials/delete.html'})
            .when('/search', {templateUrl: '/html/partials/search.html'})
            .when('/help', {templateUrl: '/html/partials/help.html'})
            .otherwise({redirectTo: '/help'});
        $sceDelegateProvider.resourceUrlWhitelist([
            'self',
            'http://localhost:8080/**'
        ]);
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
    .directive('pmSection', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: '/html/elements/pm-section.html'
        }
    })
    .directive('pmFabToolbar', function() {
        return {
            restrict: 'E',
            templateUrl: '/html/elements/pm-fab-toolbar.html'
        }
    })
    /* controllers section */
    .controller('helpCtrl', ['$scope', '$http', '$templateCache', function($scope, $http, $templateCache) {
        $scope.method = 'GET';
        $scope.url = '/html/test/test.html';
        $scope.feedback = function() {
            $scope.code = null;
            $scope.response = null;
            $http({method: $scope.method, url: $scope.url, cache: $templateCache}).
                then(function(response) {
                    $scope.status = response.status;
                    $scope.data = response.data;
                }, function(response) {
                    $scope.data = response.data || 'error: request failed';
                    $scope.status = response.status;
                });
        };
    }])
    .controller('indexCtrl', ['$scope', function($scope) {

    }]);
})();
