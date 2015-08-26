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
			close : jasmine.createSpy('modalInstance.close'),
			dismiss : jasmine.createSpy('modalInstance.dismiss'),
			result : {
				then : jasmine.createSpy('modalInstance.result.then')
			}
		};
		var exampleBook = {
			'id' : 1,
			'title' : 'title',
			'authors' : [ {
				'id' : 7,
				'firstName' : 'Jan',
				'lastName' : 'Kowalski'
			} ]
		};
		var exampleHeader = function() {
			return 'Editing book';
		};
		ctrl = $controller('BookModalController', {
			$scope : $scope,
			$modalInstance : modalInstance,
			book : exampleBook,
			header : exampleHeader
		});
	}));

	it('addBook is defined', inject(function() {
		// then
		expect($scope.addBook).toBeDefined();
	}));

	it('addBook calls bookService.addBook', inject(function( $q, bookService) {
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
	
	it('addAuthoris defined', inject(function() {
		// then
		expect($scope.addAuthor).toBeDefined();
	}));
	
	it('addAuthor adds Author', inject(function() {
		// given
		var spy = spyOn($scope.books,'push');
		// when
		
		// then
	}));
	
	
});
