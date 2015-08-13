package pl.spring.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private BookService bookService;

	@Test
	public void testShouldRemoveLibraryAndBooks() {
		// given
		final long id = 13;
		List<BookTo> allBooks = bookService.findAllBooks();
		List<BookTo> libraryBooks = bookService.findBookByLibrary_Id(id);
		List<BookTo> expectedBooks = new ArrayList<>(allBooks);
		expectedBooks.removeAll(libraryBooks);
		// when
		libraryService.removeLibraryById(id);
		// then
		List<BookTo> leftBooks = bookService.findAllBooks();
		assertEquals(allBooks.size()-libraryBooks.size(),leftBooks.size());
		assertThat(leftBooks,is(expectedBooks));
	}

}

