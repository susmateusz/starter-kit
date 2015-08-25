describe('book modal controller', function () {
    'use strict';
    
//    var modalInstance = {close: angular.noop, dismiss: angular.noop};
    var $scope;

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));
//    
//	it('search is defined', inject(function($controller) {
//		// when
//		$controller('BookModalController', {
//			$scope : scope
//		});
//		// then
//		expect(BookModalController.addBook).toBeDefined();
//	}));
});






























