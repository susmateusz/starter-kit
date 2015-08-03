package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(7, allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
        assertTrue(booksByTitle.size()>1);
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
    	// given
    	final String author = "";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test
    public void testShouldFindBookByTitle() {
    	// given
    	final String title = "Opium";
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
    	// then
    	assertNotNull(booksByTitle);
    	assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindBookByFirstAndLastName() {
    	// given
    	final String author = "Edmund Niziurski";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test
    public void testShouldFindBookByFirstNamePrefix() {
    	// given
    	final String author = "Ed";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test
    public void testShouldFindBookByLastNamePrefix() {
    	// given
    	final String author = "Niz";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
    
    /**
     * author: MATSUS
     */
    @Test
    public void testShouldHaveNotNullIdException() {
    	// given
    	final BookTo bookToSave = new BookTo();
    	bookToSave.setId(null);
    	// when
    	BookTo result = bookService.saveBook(bookToSave);
       	// then
    	assertTrue(result.getId()!=null);
    }
    
}
