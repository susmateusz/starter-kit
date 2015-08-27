describe('book search service', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
		module('app.books');
	});

	var $scope;
	beforeEach(inject(function($rootScope) {
		$scope = $rootScope.$new();
	}));

	it('search is defined', inject(function(bookService) {
		// then
		expect(bookService.search).toBeDefined();
	}));

	it('search book should call bookRestService.search', inject(function(
			bookService, bookRestService) {
		// given
		var prefix = '';
		spyOn(bookRestService, 'search').and.returnValue();
		// when
		bookService.search(prefix);
		// then
		expect(bookRestService.search).toHaveBeenCalledWith(prefix);
	}));

	it('deleteBook is defined', inject(function(bookService) {
		// then
		expect(bookService.deleteBook).toBeDefined();
	}));

	it('deleteBook should call bookRestService.deleteBook', inject(function(
			bookService, bookRestService) {
		// given
		var bookId = 3;
		spyOn(bookRestService, 'deleteBook').and.returnValue();
		// when
		bookService.deleteBook(bookId);
		// then
		expect(bookRestService.deleteBook).toHaveBeenCalledWith(bookId);
	}));

	it('addBook is defined', inject(function(bookService) {
		// then
		expect(bookService.addBook).toBeDefined();
	}));

	it('addBook should call bookRestService.addBook', inject(function(bookService,
			bookRestService) {
		// given
		var book = {
			'id' : 1,
			'title' : 'title',
			'authors' : [ {
				'id' : 7,
				'firstName' : 'Jan',
				'lastName' : 'Kowalski'
			} ]
		};
		spyOn(bookRestService, 'addBook').and.returnValue();
		// when
		bookService.addBook(book);
		// then
		expect(bookRestService.addBook).toHaveBeenCalledWith(book);
	}));

});