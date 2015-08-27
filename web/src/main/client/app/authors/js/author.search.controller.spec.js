describe('book controller', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
		module('flash');
		module('app.authors');
	});

	var $scope;
	var ctrl;

	beforeEach(inject(function($rootScope, $controller) {
		$scope = $rootScope.$new();
		ctrl = $controller('AuthorSearchController', {
			$scope : $scope
		});
	}));

	it('findAll author is defined', inject(function() {
		// then
		expect($scope.findAll).toBeDefined();
	}));

	it('findAll author should call authorService.findAll', inject(function($q, authorService) {
		// given
		var result = {
			'data' : [ {
				'id' : 7, 'firstName' : 'Jan', 'lastName' : 'Kowalski'
			} ]
		};
		var searchDeferred = $q.defer();
		spyOn(authorService, 'findAll').and.returnValue(searchDeferred.promise);
		// when
		$scope.findAll();
		searchDeferred.resolve(result);
		$scope.$digest();
		// then
		expect(authorService.findAll).toHaveBeenCalled();
		expect($scope.authors.length).toBe(1);
		expect($scope.authors[0]).toEqual(result.data[0]);
	}));
	
	it('findAll author should fail call authorService', inject(function($q,authorService,Flash) {
		// given
		var searchDeferred = $q.defer();
		spyOn(authorService, 'findAll').and.returnValue(searchDeferred.promise);
		spyOn(Flash,'create');
		// when
		$scope.findAll();
		searchDeferred.reject();
		$scope.$digest();
		// then
		expect(authorService.findAll).toHaveBeenCalled();
		expect(Flash.create).toHaveBeenCalledWith('danger', 'Failed to load authors.', 'custom-class');
	}));

	it('filter startsWith', inject(function() {
		// then
		expect($scope.anyNameStartsWith).toBeDefined();
	}));
	
	it('anyNameStartsWith filters by firstName', inject(function() {
		//given
		$scope.prefix.name = 'jan';
		var author = {id:1,'firstName':'janusz','lastName':'kowalski'};
		//when
		var result = $scope.anyNameStartsWith(author);
		// then
		expect(result).toBe(true);
	}));
	
	it('anyNameStartsWith filters by lastName', inject(function() {
		//given
		$scope.prefix.name = 'kow';
		var author = {id:1,'firstName':'janusz','lastName':'kowalski'};
		//when
		var result = $scope.anyNameStartsWith(author);
		// then
		expect(result).toBe(true);
	}));
	
	it('anyNameStartsWith filters by wrong name prefix', inject(function() {
		//given
		$scope.prefix.name = 'walski';
		var author = {id:1,'firstName':'janusz','lastName':'kowalski'};
		//when
		var result = $scope.anyNameStartsWith(author);
		// then
		expect(result).toBe(false);
	}));
});