package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.dao.impl.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

public interface BookService {

    List<BookTo> findAllBooks();
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);

    List<BookTo> findBookByLibrary_Id(Long id);

    BookTo saveBook(BookTo book);
    
    List<BookTo> findBookBySearchCriteria(BookSearchCriteria bookSearchCriteria);
    
	BookTo deleteBookById(Long long1);
}
