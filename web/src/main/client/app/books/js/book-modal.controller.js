angular.module('app.books').controller(
		'BookModalController',
		function($scope, $modal, $window, $location, bookService,book,header) {
			'use strict';

			$scope.header = header;
			$scope.book = book;
			$scope.index = 0;
			$scope.firstName = '';
			$scope.lastName = '';
			$scope.gridOptions = {
				data : 'book'
			};

			$scope.addBook = function() {
				bookService.addBook($scope.book);
			};

			$scope.addAuthor = function(authorFirstName, authorLastName) {
				$scope.index = $scope.book.authors.length;
				$scope.book.authors[$scope.index] = {
					id: 'null',
					firstName : authorFirstName,
					lastName : authorLastName
				};
				$scope.firstName = '';
				$scope.lastName = '';
			};

			$scope.deleteAuthor = function(authorIndex) {
				$scope.book.authors.splice(authorIndex, 1);
			};
		});
