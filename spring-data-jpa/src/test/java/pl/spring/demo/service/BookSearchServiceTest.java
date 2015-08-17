package pl.spring.demo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookSearchServiceTest {

	@Autowired
	private BookSearchService bookSearchService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShouldFindByTitle() {
		// given
		final String title = "trzy";
		// when
		List<BookTo> books = bookSearchService.findBooksByTitle(title);
		// then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		for(BookTo book : books)
			assertTrue(book.getTitle().toUpperCase().startsWith(title.toUpperCase()));
	}

}
