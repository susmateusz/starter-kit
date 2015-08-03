package pl.spring.demo.dao;

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

import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BookDaoTest-context.xml")
public class BookDaoImplTest {

	@Autowired
	private BookDao bookDao;

	@Test
	public void testShouldFindAllBooks() {
		List<BookEntity> allBooks = bookDao.findAll();
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertTrue(6 <= allBooks.size());
	}

	@Test
	public void testShouldFindAllBooksByEmptyTitle() {
		// given
		final String title = "";
		// when
		List<BookEntity> booksByTitle = bookDao.findBookByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksByTitlePart() {
		// given
		final String title = "Opium";
		// when
		List<BookEntity> booksByTitle = bookDao.findBookByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test
	public void testShouldNotFindAnyBookByNonExistingTitle() {
		// given
		final String title = "**************";
		// when
		List<BookEntity> booksByTitle = bookDao.findBookByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertTrue(booksByTitle.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksWhenEmptyAuthor() {
		// given
		final String author = "";
		// when
		List<BookEntity> booksByAuthor = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
		assertEquals(6, booksByAuthor.size());
	}

	@Test
	public void testShouldFindAllBooksByFullName() {
		// given
		final String author = "Hanna Ożogowska";
		// when
		List<BookEntity> booksByAuthor = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksByFirstNamePart() {
		// given
		final String author = "anna";
		// when
		List<BookEntity> booksByAuthor = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksByLastNamePart() {
		// given
		final String author = "gowska";
		// when
		List<BookEntity> booksByAuthor = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
	}

	@Test
	public void testShouldNotFindAnyBookByNonExistingAuthor() {
		// given
		final String author = "****************";
		// when
		List<BookEntity> booksByAuthor = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertTrue(booksByAuthor.isEmpty());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		final BookEntity bookToSave = new BookEntity();
		bookToSave.setId(22L);
		// when
		bookDao.save(bookToSave);
		// then
		fail("test should throw BookNotNullIdException");
	}

	@Test
	public void testBookShouldHaveNotNullIdException() {
		// given
		final BookEntity bookToSave = new BookEntity();
		bookToSave.setId(null);
		// when
		BookEntity result = bookDao.save(bookToSave);
		// then
		assertNotNull(result);
		assertNotNull(result.getId());
	}
}
