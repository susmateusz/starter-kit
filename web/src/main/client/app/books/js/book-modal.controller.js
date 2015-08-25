angular.module('app.books').controller(
		'BookModalController',
		function($scope, $modalInstance,
				bookService, book, header) {
			'use strict';

			$scope.header = header;
			$scope.book = book;
			$scope.firstName = '';
			$scope.lastName = '';
			$scope.gridOptions = {
				data : 'book'
			};

			$scope.addBook = function() {
				if ($scope.book.title !== '' && $scope.book.authors.length > 0) {
					bookService.addBook($scope.book);
					$scope.$close(book);
				}
			};

			$scope.addAuthor = function(authorFirstName, authorLastName) {
				$scope.index = $scope.book.authors.length;
				$scope.book.authors.push({
					id : 'null',
					firstName : authorFirstName,
					lastName : authorLastName
				});
				$scope.firstName = '';
				$scope.lastName = '';
			};

			$scope.deleteAuthor = function(authorIndex) {
				$scope.book.authors.splice(authorIndex, 1);
			};
		});
