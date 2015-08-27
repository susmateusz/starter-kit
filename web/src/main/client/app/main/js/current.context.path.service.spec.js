describe('cuttent context path service test', function() {
	'use strict';
	
	beforeEach(function() {
		module('app.main');
	});
	
	var $scope;
	var $currentContextPath;
	
	beforeEach(inject(function($rootScope,currentContextPath) {
		$scope = $rootScope.$new();
		$currentContextPath = currentContextPath;
	}));
	
	if('get is defined', inject(function() {
		// then
		expect($currentContextPath.get).toBeDefined();
	}));
});