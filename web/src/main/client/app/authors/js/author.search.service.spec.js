describe('author search service', function() {
	'use strict';
	beforeEach(function() {
		module('app.main');
		module('app.authors');
	});
	
	var $scope;
	var $authorService;
	var $authorRestService;
	
	beforeEach(inject(function($rootScope,authorService,authorRestService) {
		$scope = $rootScope.$new();
		$authorService = authorService;
		$authorRestService = authorRestService;
	}));
	
	if('findAll is defined', inject(function() {
		// then
		expect($authorService.findAll).toBeDefined();
	}));
	
	it('search author call authorRestService.findAll',inject(function($q) {
		// given
		var searchDeferred = $q.defer();
		spyOn($authorRestService,'findAll').and.returnValue(searchDeferred.promise);
		// when
		$authorService.findAll();
		searchDeferred.resolve();
		$scope.$digest();
		// then
		expect($authorRestService.findAll).toHaveBeenCalled();
	}));
	
});