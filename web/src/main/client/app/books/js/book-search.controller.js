angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.prefix = {title:''};
    $scope.titleWidth="col-md-3";

    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };

    $scope.search = function () {
        bookService.search().then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };
    
    $scope.startsWith = function(title,prefix) {
    	return title.substr(0,prefix.length).toLowerCase() == prefix.toLowerCase();
    }

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Książka została usunięta!', 'custom-class');
        });
    };

    $scope.addBook = function () {
        $modal.open({
            templateUrl: 'books/html/book-modal.html',
            controller: 'BookModalController',
            size: 'lg',
            resolve: {
            	header : function(){
            		return 'Inserting new book';
            	},
            	book : function(){
            		return {id:'null',title:'',authors:[]};
            	}
            }
        }).result.then(function(response){
        	Flash.create('success', 'Książka "'+response.title+'" została dodana!', 'custom-class');
        	$scope.books.push(response);
        });
    };
    
    $scope.editBook = function(book) {
    	$modal.open({
            templateUrl: 'books/html/book-modal.html',
            controller: 'BookModalController',
            size: 'lg',
            resolve: {
            	header : function(){
            		return 'Editing book';
            	},
            	book : function(){
            		return book;
            	}
            }
    	}).result.then(function(response){
        	Flash.create('success', 'Książka "'+response.title+'" została edytowana!', 'custom-class');
        	$scope.books.push(response);
        });
    };

});
