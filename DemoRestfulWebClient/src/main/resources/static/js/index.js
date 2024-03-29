(function () {
    'use strict';
    angular.module('indexApp', [
        'ngMaterial',
        'ngRoute',
        'ngMessages'
    ])
    /* config section */
    .config(['$routeProvider', '$sceDelegateProvider', function($routeProvider) {
        $routeProvider
            .when('/create', {templateUrl: '/html/partials/create.html'})
            .when('/read', {templateUrl: '/html/partials/read.html'})
            .when('/update', {templateUrl: '/html/partials/update.html'})
            .when('/delete', {templateUrl: '/html/partials/delete.html'})
            .when('/search', {templateUrl: '/html/partials/search.html'})
            .when('/sync', {templateUrl: '/html/partials/sync.html'})
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
        $scope.url = 'http://localhost:8080/html/probes/probe-one.html';
        $scope.feedback = function() {
            $http({
                method: $scope.method,
                url: $scope.url,
                cache: $templateCache
            })
                .then(function(response) {
                    $scope.status = response.status;
                    $scope.data = response.data;
                }, function(response) {
                    $scope.status = response.status;
                    $scope.data = response.data || 'request failed';
                });
        };
    }])
    .controller('syncCtrl', ['$scope', '$http', '$templateCache', function($scope, $http, $templateCache) {
        $scope.fetch = function() {
            var successFetch = function(response) {
                $scope.status = response.status;
                $scope.data = response.data;
            };
            var errorFetch = function(response) {
                $scope.status = response.status;
                $scope.data = response.data || 'request failed';
            };
            $http({
                method: 'GET',
                url: 'http://localhost:8080/json/sample-one.json',
                cache: $templateCache
            }).then(successFetch, errorFetch);
        };
    }])
    .controller('readCtrl', ['$scope', '$http', '$sce', function($scope, $http, $sce) {
        $scope.fetchResponse = function() {
            var url = "http://127.0.0.1:9090/api/vines";
            var trustedUrl = $sce.trustAsResourceUrl(url);
            $http.jsonp(trustedUrl, {jsonpCallbackParam: 'callback'})
                .then(function(response) {
                    $scope.status = response.status;
                    $scope.data = response.data;
                }, function(response) {
                    $scope.status = response.status;
                    $scope.help = response.data || 'request failed';
                });
        };
    }])
    .controller('indexCtrl', ['$scope', function($scope) {

    }]);
})();
