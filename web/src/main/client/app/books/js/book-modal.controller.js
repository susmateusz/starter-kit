angular.module('app.books').controller(
		'BookModalController',
		function($scope, $modal, Flash) {
			'use strict';

			$scope.bookTitle = '';
			$scope.authors = [];
			$scope.index = 0;
			$scope.firstName = '';
			$scope.lastName = '';
			$scope.gridOptions = {
				data : 'authors'
			};

			$scope.addBook = function() {
				$close();
				var book ={title : $scope.bookTitle, authors : $scope.authors};
				bookService.addBook($book).then(function(){
					Flash.create('success', 'Książka '+$book+' została dodana!', 'custom-class');
				}, function(){
					Flash.create('danger', 'Wystapił problem  podczas dodawania książki!', 'custom-class');
				});
			};

			$scope.addAuthor = function(authorFirstName, authorLastName) {
				$scope.index = $scope.authors.length;
				$scope.authors[$scope.index] = {
					firstName : authorFirstName,
					lastName : authorLastName
				};
				$scope.firstName = '';
				$scope.lastName = '';
			};

			$scope.deleteAuthor = function(authorIndex) {
				$scope.authors.splice(authorIndex, 1);
			};
		});
