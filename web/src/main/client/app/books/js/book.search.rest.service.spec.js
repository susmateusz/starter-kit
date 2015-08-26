describe('book rest service', function() {
	'use strict';

	var $scope;
	
	beforeEach(function(){
		module('app.main');
		module('app.books');
	});
	
	
	beforeEach(inject(function($rootScope){
		$scope = $rootScope.$new();
	}));
	
	
	
	
});