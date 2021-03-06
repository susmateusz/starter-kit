package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    
    @RequestMapping(value = "/books/remove/{bookId}/")
    public String bookRemove(Map<String, Object> params,@PathVariable("bookId") long bookId) {
    	final BookTo deletedBook = bookService.deleteBookById(new Long(bookId));
    	params.put("deletedBookTitle", deletedBook.getTitle());
//    	params.put("deletedBook", deletedBook)
    	return "bookDeleted";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }
}
