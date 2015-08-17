package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.to.BookTo;

public interface BookSearchService {

	List<BookTo> findBooksByTitle(String title);

	List<BookTo> findBooksByAuthor(String author);

	List<BookTo> findBooksByLibraryName(String libraryName);

}