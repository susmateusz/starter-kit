package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Ignore;
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
		Predicate<BookTo> ifTitleMatches = s -> s.getTitle().toUpperCase().startsWith(title.toUpperCase());
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		long expected = allBooks.stream().filter(ifTitleMatches).count();
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expected, resultBooks.size());
		for (BookTo book : resultBooks)
			assertTrue(book.getTitle().toUpperCase().startsWith(title.toUpperCase()));
	}

	@Test
	public void testShouldFindByAuthor() {
		// given
		final String author = "now";
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, author, null);
		Predicate<BookTo> ifTitleMatches = s -> s.getAuthors().toUpperCase().contains(author.toUpperCase());
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		long expectedSize = allBooks.stream().filter(ifTitleMatches).count();
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expectedSize, resultBooks.size());
		for (BookTo book : resultBooks) {
			String resultAuthor = book.getAuthors().toUpperCase();
			String expectedAuthor = author.toUpperCase();
			assertTrue(resultAuthor.contains(expectedAuthor));
		}
	}

	@Test
	public void testShouldFindByLibraryName() {
		// given
		final String libraryName = "Biblioteka Miejska";
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, libraryName);
		Predicate<BookTo> ifLibraryMatches = s -> s.getLibraryName().toUpperCase().contains(libraryName.toUpperCase());
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		long expectedSize = allBooks.stream().filter(ifLibraryMatches).count();
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expectedSize, resultBooks.size());
		for (BookTo book : resultBooks)
			assertTrue(book.getLibraryName().toUpperCase().startsWith(libraryName.toUpperCase()));
	}

	@Test
	public void testShouldFindAllBooks() {
		// given
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, null);
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		long expectedSize = allBooks.size();
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expectedSize, resultBooks.size());
	}

	@Test
	public void testShouldFindByAllCriterias() {
		// given
		String bookTitle = "Piętnasta książka";
		String bookAuthors = "Zbigniew Nowak";
		String bookLibrary = "Biblioteka Miejska";
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(bookTitle, bookAuthors, bookLibrary);
		// when
		long expectedSize = 1;
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(expectedSize, resultBooks.size());
		assertEquals(bookTitle, resultBooks.get(0).getTitle());
		assertEquals(bookAuthors, resultBooks.get(0).getAuthors());
		assertEquals(bookLibrary, resultBooks.get(0).getLibraryName());
	}

	@Test
	public void testShouldNotFindBook() {
		// given
		String bookTitle = "**************";
		final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(bookTitle, null, null);
		// when
		List<BookTo> resultBooks = bookService.findBookBySearchCriteria(bookSearchCriteria);
		// then
		assertNotNull(resultBooks);
		assertTrue(resultBooks.isEmpty());
	}

}
