describe('book modal controller', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
		module('app.books');
	});

	var $scope;
	var modalInstance;
	beforeEach(inject(function($rootScope) {
		$scope = $rootScope.$new();
		modalInstance = {
			close : jasmine.createSpy('modalInstance.close'),
			dismiss : jasmine.createSpy('modalInstance.dismiss')
		};
	}));

	xit('addBook is defined', inject(function($controller) {
		$controller('BookModalController', {
			$scope : $scope,
			$modalInstance : modalInstance
		});
		expect($scope.addBook).toBeDefined();
	}));

	xit('addBook calls bookService.addBook', inject(function($controller, $q,
			bookService) {
		// given
		$controller('BookModalController', {
			$scope : $scope
		});
		var book = {
			'id' : 1,
			'title' : 'title',
			'authors' : [ {
				'id' : 7,
				'firstName' : 'Jan',
				'lastName' : 'Kowalski'
			} ]
		};
		var searchDeferred = $q.defer();
		spyOn(bookService, 'addBook').and.returnValue(searchDeferred.promise);
		// when
		$scope.addBook();
		searchDeferred.resolve(result);
		$scope.$digest();
		// then
		expect(bookService.addBook).toHaveBeenCalled();
	}));
});