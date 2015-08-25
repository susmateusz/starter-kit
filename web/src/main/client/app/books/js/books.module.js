angular.module('app.books', [ 'ngRoute' ]).config(function($routeProvider) {
	'use strict';
	$routeProvider.when('/books/book-list', {
		templateUrl : 'books/html/book-list.html',
		controller : 'BookSearchController'
	});
	$routeProvider.when('/books/book-modal', {
		templateUrl : 'books/html/book-modal.html',
		controller : 'BookModalController'
	});

});                      