describe('book controller', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
		module('flash');
		module('app.books');
	});

	var $scope;
	var modalInstance;
	var ctrl;

	beforeEach(inject(function($rootScope, $controller) {
		$scope = $rootScope.$new();
		modalInstance = {
			close : jasmine.createSpy('modalInstance.close'), dismiss : jasmine.createSpy('modalInstance.dismiss'),
			// result : defferedPromise
			result : {
				then : jasmine.createSpy('modalInstance.result.then')
			}
		};
		var exampleBooks = [ {
			'id' : 1, 'title' : 'title', 'authors' : [ {
				'id' : 7, 'firstName' : 'Jan', 'lastName' : 'Kowalski'
			} ]
		} ];
		ctrl = $controller('BookSearchController', {
			$scope : $scope, books : exampleBooks,
		});
	}));

	it('filter should detect right title', inject(function() {
		// given
		var title = 'Pierwsza ksiazka';
		var prefix = 'Pierw';
		// when
		var result = $scope.startsWith(title, prefix);
		// then
		expect(result).toBeTruthy();
	}));

	it('filter should detect any book with prefix', inject(function() {
		// given
		var title = 'Pierwsza ksiazka';
		var prefix = 'aPierw';
		// when
		var result = $scope.startsWith(title, prefix);
		// then
		expect(result).toBeFalsy();
	}));

	it('search is defined', inject(function() {
		// then
		expect($scope.search).toBeDefined();
	}));

	it('search book should call bookService.search', inject(function($q, bookService) {
		// given
		var result = {
			'data' : [ {
				'id' : 1, 'title' : 'title', 'authors' : [ {
					'id' : 7, 'firstName' : 'Jan', 'lastName' : 'Kowalski'
				} ]
			} ]
		};
		var searchDeferred = $q.defer();
		spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
		// when
		$scope.search();
		searchDeferred.resolve(result);
		$scope.$digest();
		// then
		expect(bookService.search).toHaveBeenCalled();
		expect($scope.books.length).toBe(1);
		expect($scope.books[0].id).toEqual(1);
		expect($scope.books[0].title).toEqual('title');
		expect($scope.books[0].authors).not.toBeNull();
	}));

	it('search book should fail call bookService', inject(function($q, bookService, Flash) {
		// given
		var searchDeferred = $q.defer();
		spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
		spyOn(Flash, 'create');
		// when
		$scope.search();
		searchDeferred.reject();
		$scope.$digest();
		// then
		expect(bookService.search).toHaveBeenCalled();
		expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
	}));

	it('deleteBook is defined', inject(function() {
		// then
		expect($scope.deleteBook).toBeDefined();
	}));

	it('delete book should call bookService.deleteBook', inject(function($q, bookService, Flash) {
		// given
		var bookId = 1;
		$scope.books = [ {
			id : 2, title : 'test2'
		}, {
			id : bookId, title : 'test'
		} ];
		var deleteDeferred = $q.defer();
		spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
		spyOn(Flash, 'create');
		// when
		$scope.deleteBook(bookId);
		deleteDeferred.resolve();
		$scope.$digest();
		// then
		expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
		expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została usunięta!', 'custom-class');
		expect($scope.books.length).toBe(1);
	}));
	
	it('delete book should fail and call Flash warning', inject(function($q, bookService, Flash) {
		// given
		var bookId = 1;
		$scope.books = [ {
			id : 2, title : 'test2'
		}, {
			id : bookId, title : 'test'
		} ];
		var deleteDeferred = $q.defer();
		spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
		spyOn(Flash, 'create');
		// when
		$scope.deleteBook(bookId);
		deleteDeferred.reject();
		$scope.$digest();
		// then
		expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
		expect(Flash.create).toHaveBeenCalledWith('danger', 'Książka nie została usunięta!', 'custom-class');
		expect($scope.books.length).toBe(2);
	}));

	it('addBook is defined', inject(function() {
		// then
		expect($scope.addBook).toBeDefined();
	}));

	it('add book should open book-modal', inject(function($modal, $q, Flash) {
		// given
		var modalDeferred = $q.defer();
		var book = {
			id : 5, title : 'test'
		};
		var expectedParams = {
			templateUrl : 'books/html/book-modal.html', controller : 'BookModalController', size : 'lg', resolve : {
				header : function() {
					return 'Inserting new book';
				}, book : function() {
					return {
						id : undefined, title : '', authors : []
					};
				}
			}
		};
		var spy = spyOn($modal, 'open').and.returnValue({
			result : modalDeferred.promise
		});
		spyOn(Flash, 'create');
		spyOn($scope.books, 'push');
		// when
		$scope.addBook();
		modalDeferred.resolve(book);
		$scope.$digest();
		// then
		expect($modal.open).toHaveBeenCalled();
		var resultParams = spy.calls.mostRecent().args[0];
		expect(resultParams.templateUrl).toEqual(expectedParams.templateUrl);
		expect(resultParams.controller).toEqual(expectedParams.controller);
		expect(resultParams.size).toEqual(expectedParams.size);
		expect(resultParams.resolve.header()).toEqual(expectedParams.resolve.header());
		expect(resultParams.resolve.book()).toEqual(expectedParams.resolve.book());
		expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "' + book.title + '" została dodana!',
				'custom-class');
		expect($scope.books.push).toHaveBeenCalledWith(book);
	}));

	it('add book should fails', inject(function($modal, $q, Flash) {
		// given
		var modalDeferred = $q.defer();
		spyOn($modal, 'open').and.returnValue({
			result : modalDeferred.promise
		});
		spyOn(Flash, 'create');
		// when
		$scope.addBook();
		modalDeferred.reject();
		$scope.$digest();
		// then
		expect($modal.open).toHaveBeenCalled();
		expect(Flash.create).toHaveBeenCalledWith('danger', 'Książka nie została dodana!', 'custom-class');
	}));

	it('editBook is defined', inject(function() {
		// then
		expect($scope.editBook).toBeDefined();
	}));

	it('edit book should open book-modal', inject(function($modal, $q, Flash) {
		// given
		var modalDeferred = $q.defer();
		var book = {
			id : 5, title : 'test'
		};
		var expectedParams = {
			templateUrl : 'books/html/book-modal.html', controller : 'BookModalController', size : 'lg', resolve : {
				header : function() {
					return 'Editing book';
				}, book : function() {
					return book;
				}
			}
		};
		var spy = spyOn($modal, 'open').and.returnValue({
			result : modalDeferred.promise
		});
		spyOn(Flash, 'create');
		spyOn($scope.books, 'push');
		// when
		$scope.editBook(book);
		modalDeferred.resolve(book);
		$scope.$digest();
		// then
		expect($modal.open).toHaveBeenCalled();
		var resultParams = spy.calls.mostRecent().args[0];
		expect(resultParams.templateUrl).toEqual(expectedParams.templateUrl);
		expect(resultParams.controller).toEqual(expectedParams.controller);
		expect(resultParams.size).toEqual(expectedParams.size);
		expect(resultParams.resolve.header()).toEqual(expectedParams.resolve.header());
		expect(resultParams.resolve.book()).toEqual(expectedParams.resolve.book());
		expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "' + book.title + '" została edytowana!',
				'custom-class');
		expect($scope.books.push).toHaveBeenCalledWith(book);
	}));

	it('edit book should open book-modal', inject(function($modal, $q, Flash) {
		// given
		var book = {
			id : 5, title : 'test'
		};
		var modalDeferred = $q.defer();
		spyOn($modal, 'open').and.returnValue({
			result : modalDeferred.promise
		});
		spyOn(Flash, 'create');
		// when
		$scope.editBook(book);
		modalDeferred.reject();
		$scope.$digest();
		// then
		expect($modal.open).toHaveBeenCalled();
		expect(Flash.create).toHaveBeenCalledWith('danger', 'Książka nie została edytowana!', 'custom-class');
	}));

});
