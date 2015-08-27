describe('app controller', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
	});

	var $scope;
	var ctrl;

	beforeEach(inject(function($rootScope, $controller) {
		$scope = $rootScope.$new();
		ctrl = $controller('AppCntl', {
			$scope : $scope
		});
	}));
	
	it('message is defined', inject(function() {
		// then
		expect($scope.message).toBeDefined();
	}));
	
	
});


