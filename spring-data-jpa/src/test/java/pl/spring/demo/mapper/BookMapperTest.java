package pl.spring.demo.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {

	private BookMapper bookMapper = new BookMapper();

	@Test
	public void testShouldReturnNullWhenNullBookEntity() {
		// given
		BookEntity bookEntity = null;
		// when
		BookTo bookTo = bookMapper.mapToBookTo(bookEntity);
		// then
		assertNull(bookTo);

	}
	
	@Test
	public void testShouldReturnNullWhenNullBookTo() {
		// given
		BookTo bookTo = null;
		// when
		BookEntity bookEntity = bookMapper.mapToBookEntity(bookTo);
		// then
		assertNull(bookEntity);
		
	}
	
	@Test
	public void testShouldConvertBookEntityToBookTo(){
		// given
		BookEntity bookEntity = new BookEntity(10L,"title",new ArrayList<>());
		bookEntity.setAuthors(bookEntity.getTestAuthorsTo());
		// when
		BookTo bookTo = bookMapper.mapToBookTo(bookEntity);
		// then
		assertEquals(bookEntity.getId(),bookTo.getId());
		assertEquals(bookEntity.getTitle(), bookTo.getTitle());
		assertEquals("1 John Doe, 2 Jan Nowak, 3 Marian Kowalski", bookTo.getAuthors());
	}
	
	@Test
	public void testShouldConvertBookToToBookEntity(){
		// given
		BookTo bookTo = new BookTo(10L,"title","1 John Doe, 2 Jan Nowak, 3 Marian Kowalski");
		// when
		BookEntity bookEntity = bookMapper.mapToBookEntity(bookTo);
		List<AuthorTo> expected = bookEntity.getTestAuthorsTo();
		List<AuthorTo> result = bookEntity.getAuthors();
		// then
		System.out.println(expected);
		System.out.println(result);
		assertEquals(bookTo.getId(),bookEntity.getId());
		assertEquals(bookTo.getTitle(), bookEntity.getTitle());
		assertTrue(expected.containsAll(result) && result.containsAll(expected) );
	}

}
