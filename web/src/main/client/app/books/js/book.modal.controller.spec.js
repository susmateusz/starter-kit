describe('book modal controller', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
		module('app.books');
	});

	var $scope;
	var modalInstance;
	var ctrl;

	beforeEach(inject(function($rootScope, $controller) {
		$scope = $rootScope.$new();
		modalInstance = {
			close : jasmine.createSpy('modalInstance.close'), dismiss : jasmine.createSpy('modalInstance.dismiss'),
			result : {
				then : jasmine.createSpy('modalInstance.result.then')
			}
		};
		var exampleBook = {
			'id' : 1, 'title' : 'title', 'authors' : [ {
				'id' : 7, 'firstName' : 'Jan', 'lastName' : 'Kowalski'
			} ]
		};
		var exampleHeader = function() {
			return 'Editing book';
		};
		ctrl = $controller('BookModalController', {
			$scope : $scope, $modalInstance : modalInstance, book : exampleBook, header : exampleHeader
		});
	}));

	it('addBook is defined', inject(function() {
		// then
		expect($scope.addBook).toBeDefined();
	}));

	it('addBook calls bookService.addBook', inject(function($q, bookService) {
		// given
		$scope.$close = jasmine.createSpy('$close');
		var addBookDeferred = $q.defer();
		spyOn(bookService, 'addBook').and.returnValue(addBookDeferred.promise);
		// when
		$scope.addBook();
		addBookDeferred.resolve();
		$scope.$digest();
		// then
		expect(bookService.addBook).toHaveBeenCalled();
		expect($scope.$close).toHaveBeenCalled();
	}));
	
	it('addBook cannot addBook', inject(function($q, bookService) {
		// given
		$scope.book.title='';
		var addBookDeferred = $q.defer();
		$scope.$close = jasmine.createSpy('$close');
		spyOn(bookService, 'addBook').and.returnValue(addBookDeferred.promise);
		// when
		$scope.addBook();
		// then
		expect(bookService.addBook).not.toHaveBeenCalled();
		expect($scope.$close).not.toHaveBeenCalled();
	}));

	it('addAuthors is defined', inject(function() {
		// then
		expect($scope.addAuthor).toBeDefined();
	}));

	it('addAuthor adds Author', inject(function() {
		// given
		var authorFirstName = 'first';
		var authorLastName = 'last';
		var spy = spyOn($scope.book.authors, 'push');
		// when
		$scope.addAuthor(authorFirstName,authorLastName);
		var pushArguments = spy.calls.mostRecent().args[0];
		// then
		expect($scope.book.authors.push).toHaveBeenCalled();
		expect(pushArguments.id).toEqual('null');
		expect(pushArguments.firstName).toEqual(authorFirstName);
		expect(pushArguments.lastName).toEqual(authorLastName);
		expect($scope.firstName).toEqual('');
		expect($scope.lastName).toEqual('');
	}));
	
	it('addAuthor adds Author', inject(function() {
		// given
		var authorFirstName = 'first';
		var authorLastName = 'last';
		var spy = spyOn($scope.book.authors, 'push');
		// when
		$scope.addAuthor(authorFirstName,authorLastName);
		var pushArguments = spy.calls.mostRecent().args[0];
		// then
		expect($scope.book.authors.push).toHaveBeenCalled();
		expect(pushArguments.id).toEqual('null');
		expect(pushArguments.firstName).toEqual(authorFirstName);
		expect(pushArguments.lastName).toEqual(authorLastName);
		expect($scope.firstName).toEqual('');
		expect($scope.lastName).toEqual('');
	}));
	
	it('deleteAuthor is defined', inject(function() {
		// then
		expect($scope.deleteAuthor).toBeDefined();
	}));
	
	it('deleteAuthors should delete author', inject(function() {
		// given
		var authorIndex = 2;
		spyOn($scope.book.authors, 'splice');
		// when
		$scope.deleteAuthor(authorIndex);
		// then
		expect($scope.book.authors.splice).toHaveBeenCalledWith(authorIndex,1);
	}));

});
