angular.module('app.authors').controller('AuthorSearchController', function($scope, authorService, Flash) {
	'use strict';

	$scope.authors = [];
	$scope.gridOptions = {
		data : 'authors'
	};
	$scope.prefix = {
			name : ''
	};

	$scope.findAll = function() {
		authorService.findAll().then(function(response) {
			angular.copy(response.data, $scope.authors);
		}, function() {
			Flash.create('danger', 'Failed to load authors.', 'custom-class');
		});
	};

	$scope.anyNameStartsWith = function(author) {
		var prefix = $scope.prefix.name;
		var firstNameMatch = author.firstName.substr(0, prefix.length).toLowerCase() === prefix.toLowerCase();
		var lastNameMatch = author.lastName.substr(0, prefix.length).toLowerCase() === prefix.toLowerCase();
		return firstNameMatch || lastNameMatch;
	};
});