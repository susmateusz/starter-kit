angular.module('app.books').controller('AddBookController', function ($scope, $window, $location, bookService, Flash) {
    'use strict';

    $scope.addBook = function (bookTitle,authorFirstName,authorLastName) {
            Flash.create('success', 'Książka "'+bookTitle+'"została dodana!', 'custom-class');
    };

});