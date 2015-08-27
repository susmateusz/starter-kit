describe('book rest service', function() {
	'use strict';

	var $scope;
	var httpBackend;
	var $bookRestService;
	beforeEach(function() {
		module('app.main');
		module('app.books');
	});

	beforeEach(inject(function($rootScope, $httpBackend, bookRestService) {
		$scope = $rootScope.$new();
		httpBackend = $httpBackend;
		$bookRestService = bookRestService;
	}));

	afterEach(function() {
		httpBackend.verifyNoOutstandingExpectation();
		httpBackend.verifyNoOutstandingRequest();
	});

	it('search rest service is defined', inject(function() {
		// then
		expect($bookRestService.search).toBeDefined();
	}));

	it('deleteBook rest service is defined', inject(function() {
		// then
		expect($bookRestService.deleteBook).toBeDefined();
	}));

	it('addBook rest service is defined', inject(function() {
		// then
		expect($bookRestService.addBook).toBeDefined();
	}));

	it('search rest service should return books', inject(function() {
		// given
		var prefix = 't';
		var url = '/context.html/rest/books/books-by-title?titlePrefix=' + prefix;
		var books = [ {
			id : 1, title : 'title1'
		} ];
		httpBackend.expectGET(url).respond(200, books);
		// when
		$bookRestService.search(prefix).then(function(response) {
			expect(response.status).toEqual(200);
			expect(response.data).toEqual(books);
		});
		// then
		httpBackend.flush();
	}));

	it('search rest service should fail', inject(function() {
		// given
		var prefix = 't';
		var url = '/context.html/rest/books/books-by-title?titlePrefix=' + prefix;
		httpBackend.expectGET(url).respond(401);
		// when
		$bookRestService.search(prefix).then(function(response) {
			expect(response.status).toEqual(401);
		});
		// then
		httpBackend.flush();
	}));

	it('delete rest service should delete book', inject(function() {
		// given
		var bookId = '1';
		var url = '/context.html/rest/books/book/' + bookId;
		httpBackend.expectDELETE(url).respond(200);
		// when
		$bookRestService.deleteBook(bookId).then(function(response) {
			expect(response.status).toEqual(200);
		});
		// then
		httpBackend.flush();
	}));

	it('delete rest service should fail', inject(function() {
		// given
		var bookId = '1';
		var url = '/context.html/rest/books/book/' + bookId;
		httpBackend.expectDELETE(url).respond(401);
		// when
		$bookRestService.deleteBook(bookId).then(function(response) {
			expect(response.status).toEqual(401);
		});
		// then
		httpBackend.flush();
	}));

	it('add rest service should add book', inject(function() {
		// given
		var book = {
			id : '1', title : 'title', authors : []
		};
		var url = '/context.html/rest/books/book';
		httpBackend.expectPOST(url, book).respond(200, book);
		// when
		$bookRestService.addBook(book).then(function(response) {
			expect(response.status).toEqual(200);
			expect(response.data).toEqual(book);
		});
		// then
		httpBackend.flush();
	}));

	it('search rest service should fail', inject(function() {
		// given
		var book = {
			id : '1', title : 'title', authors : []
		};
		var url = '/context.html/rest/books/book';
		httpBackend.expectPOST(url, book).respond(401);
		// when
		$bookRestService.addBook(book).then(function(response) {
			expect(response.status).toEqual(401);
		});
		// then
		httpBackend.flush();
	}));

});