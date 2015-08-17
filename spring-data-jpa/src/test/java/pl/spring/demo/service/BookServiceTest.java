package pl.spring.demo.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Predicate;

import org.jadira.usertype.spi.utils.lang.AssertionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.impl.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceTest {

	@Autowired
	private BookService bookService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShouldFindByTitle() {
		// given
		final String title = "trzy";
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("trzy", null, null);
		Predicate<BookTo> ifTitleHasGoodPrefix = s->s.getTitle().startsWith(title);
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		long expected = allBooks.stream().filter(ifTitleHasGoodPrefix).count();
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expected, resultBooks.size());
		for(BookTo book : resultBooks)
			assertTrue(book.getTitle().toUpperCase().startsWith(title.toUpperCase()));
	}
	
	@Test
	public void testShouldFindByAuthor() {
		// given
		final String author = "nowak";
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, author, null);
		Predicate<BookTo> ifTitleHasGoodPrefix = s->s.getAuthors().toUpperCase().contains(author.toUpperCase());
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		long expected = allBooks.stream().filter(ifTitleHasGoodPrefix).count();
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expected, resultBooks.size());
		for(BookTo book : resultBooks)
			assertTrue(book.getAuthors().toUpperCase().startsWith(author.toUpperCase()));
	}
	
	@Test
	public void testShouldFindByLibraryName() {
		// given
		final String libraryName = "Biblioteka Miejska";
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, libraryName);
		Predicate<BookTo> ifTitleHasGoodPrefix = s->s.getLibrary().getName().toUpperCase().contains(libraryName.toUpperCase());
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		long expected = allBooks.stream().filter(ifTitleHasGoodPrefix).count();
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expected, resultBooks.size());
		for(BookTo book : resultBooks)
			assertTrue(book.getLibrary().getName().toUpperCase().startsWith(libraryName.toUpperCase()));
	}

}
