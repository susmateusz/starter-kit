package pl.spring.demo.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {

	private BookMapper bookMapper;
	
	@Before
	public void setUp(){
		bookMapper = new BookMapper();
	}

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
		BookEntity bookEntity = new BookEntity(10L,"title",null);
		bookEntity.setAuthors(BookEntity.getTestAuthorsTo());
		// when
		BookTo bookTo = bookMapper.mapToBookTo(bookEntity);
		// then
		assertEquals(bookEntity.getId(),bookTo.getId());
		assertEquals(bookEntity.getTitle(), bookTo.getTitle());
		assertEquals("Wiliam Szekspir, Hanna Ożogowska, Jan Parandowski, Edmund Niziurski, Zbigniew Nienacki, Aleksander Fredro", bookTo.getAuthors());

	}
	
	@Test
	public void testShouldConvertBookToToBookEntity(){
		// given
		BookTo bookTo = new BookTo(10L,"title","Wiliam Szekspir, Hanna Ożogowska, Jan Parandowski, Edmund Niziurski, Zbigniew Nienacki, Aleksander Fredro");
		// when
		BookEntity bookEntity = bookMapper.mapToBookEntity(bookTo);
		List<AuthorTo> expected = BookEntity.getTestAuthorsTo();
		List<AuthorTo> result = bookEntity.getAuthors();
		// then
		assertEquals(bookTo.getId(),bookEntity.getId());
		assertEquals(bookTo.getTitle(), bookEntity.getTitle());
		assertThat(result, is(expected));
	}
	
	@Test
	public void testShouldConvertBookToToBookEntityWhenIdNull(){
		// given
		BookTo bookTo = new BookTo(null,"title","Wiliam Szekspir, Hanna Ożogowska, Jan Parandowski, Edmund Niziurski, Zbigniew Nienacki, Aleksander Fredro");
		// when
		BookEntity bookEntity = bookMapper.mapToBookEntity(bookTo);
		List<AuthorTo> expected = BookEntity.getTestAuthorsTo();
		List<AuthorTo> result = bookEntity.getAuthors();
		// then
		assertEquals(bookTo.getId(),bookEntity.getId());
		assertEquals(bookTo.getTitle(), bookEntity.getTitle());
		assertThat(result, is(expected));
	}

}
