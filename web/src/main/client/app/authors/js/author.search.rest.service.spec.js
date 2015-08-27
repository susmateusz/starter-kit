describe('author rest service', function() {
	'use strict';

	var $scope;
	var httpBackend;
	var $authorRestService;
	beforeEach(function() {
		module('app.main');
		module('app.authors');
	});

	beforeEach(inject(function($rootScope, $httpBackend, authorRestService) {
		$scope = $rootScope.$new();
		httpBackend = $httpBackend;
		$authorRestService = authorRestService;
	}));

	afterEach(function() {
		httpBackend.verifyNoOutstandingExpectation();
		httpBackend.verifyNoOutstandingRequest();
	});

	it('findAll rest service is defined', inject(function() {
		// then
		expect($authorRestService.findAll).toBeDefined();
	}));

	it('findAll rest service should return books', inject(function() {
		// given
		var url = '/context.html/rest/authors';
		var authors = [ {
			'id' : 7, 'firstName' : 'Jan', 'lastName' : 'Kowalski'
		} ];
		httpBackend.expectGET(url).respond(200,authors);
		//when
		$authorRestService.findAll().then(function(response) {
			expect(response.status).toEqual(200);
			expect(response.data).toEqual(authors);
		});
		// then
		httpBackend.flush();
	}));
});