describe('book controller', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
		module('flash');
		module('app.books');
	});

	var $scope;
	beforeEach(inject(function($rootScope) {
		$scope = $rootScope.$new();
	}));

	it('search is defined', inject(function($controller) {
		// when
		$controller('BookSearchController', {
			$scope : $scope
		});
		// then
		expect($scope.search).toBeDefined();
	}));

	it('search book should call bookService.search', inject(function(
			$controller, $q, bookService) {
		// given
		$controller('BookSearchController', {
			$scope : $scope
		});
		var result = {
			'data' : [ {
				'id' : 1,
				'title' : 'title',
				'authors' : [ {
					'id' : 7,
					'firstName' : 'Jan',
					'lastName' : 'Kowalski'
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

	it('search book should fail call bookService', inject(function($controller,
			$q, bookService, Flash) {
		// given
		$controller('BookSearchController', {
			$scope : $scope
		});
		var searchDeferred = $q.defer();
		spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
		spyOn(Flash, 'create');
		// when
		$scope.search();
		searchDeferred.reject();
		$scope.$digest();
		// then
		expect(bookService.search).toHaveBeenCalled();
		expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek',
				'custom-class');
	}));

	it('delete book should call bookService.deleteBook', inject(function(
			$controller, $q, bookService, Flash) {
		// given
		$controller('BookSearchController', {
			$scope : $scope
		});

		var bookId = 1;
		$scope.books = [ {
			id : bookId,
			title : 'test'
		} ];
		var deleteDeferred = $q.defer();
		spyOn(bookService, 'deleteBook').and
				.returnValue(deleteDeferred.promise);
		spyOn(Flash, 'create');
		// when
		$scope.deleteBook(bookId);
		deleteDeferred.resolve();
		$scope.$digest();
		// then
		expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
		expect(Flash.create).toHaveBeenCalledWith('success',
				'Książka została usunięta!', 'custom-class');
		expect($scope.books.length).toBe(0);
	}));

	it('add book should open book-modal', inject(function($controller) {
		// given
		$controller('BookSearchController', {
			$scope : $scope
		});

	}));

	it('filter should detect right title', inject(function($controller) {
		// given
		$controller('BookSearchController', {
			$scope : $scope
		});
		var title = 'Pierwsza ksiazka';
		var prefix = 'Pierw';
		// when
		var result = $scope.startsWith(title, prefix);
		// then
		expect(result).toBeTruthy();
	}));

	it('filter should detect any book with prefix',
			inject(function($controller) {
				// given
				$controller('BookSearchController', {
					$scope : $scope
				});
				var title = 'Pierwsza ksiazka';
				var prefix = 'aPierw';
				// when
				var result = $scope.startsWith(title, prefix);
				// then
				expect(result).toBeFalsy();
			}));
});
