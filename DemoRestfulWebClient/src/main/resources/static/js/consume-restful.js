var app = angular.module('consumeRestfulWebApp', ['ngResource']);

app.factory("samples", function($resource) {
    return $resource("http://localhost:9090/samples");
});

app.controller("SampleCtrl", function($scope, samples) {
    samples.query(function(data) {
        $scope.samples = data;
    }, function(e) {
        console.error("An error has occurred: ", e);
    });
});
