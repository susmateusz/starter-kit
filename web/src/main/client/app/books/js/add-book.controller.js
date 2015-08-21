angular.module('app.books').controller(
		'AddBookController',
		function($scope, $window, $location, bookService, Flash, $modal) {
			'use strict';
			
			$scope.title = '';

			$scope.addBook = function(bookTitle, authorFirstName,
					authorLastName) {

				Flash.create('success', 'Książka "' + bookTitle
						+ '"została dodana!', 'custom-class');
			};

		});