angular.module('app.authors').controller('AuthorSearchController', function($scope,authorService,Flash) {
	'use strict';

	$scope.prefix = '';
	$scope.authors = [];
	$scope.gridOptions = {
		data : 'authors'
	};

	$scope.findAll = function() {
		authorService.findAll().then(function(response) {
			angular.copy(response.data, $scope.authors);
		}, function() {
			Flash.create('danger', 'Failed to load authors.', 'custom-class');
		});
	};

});