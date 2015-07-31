package pl.spring.demo.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.BookMapper;
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
		BookEntity bookEntity = new BookEntity(10L,"title",null);
		bookEntity.setAuthors(bookEntity.getTestAuthorsTo());
		// when
		BookTo bookTo = bookMapper.mapToBookTo(bookEntity);
		// then
		assertEquals(bookEntity.getId(),bookTo.getId());
		assertEquals(bookEntity.getTitle(), bookTo.getTitle());
		assertEquals("John Doe, Jan Nowak, Marian Kowalski", bookTo.getAuthors());
	}
	
	@Test
	public void testShouldConvertBookToToBookEntity(){
		// given
		BookTo bookTo = new BookTo(10L,"title","John Doe, Jan Nowak, Marian Kowalski");
		// when
		BookEntity bookEntity = bookMapper.mapToBookEntity(bookTo);
		List<AuthorTo> expected = bookEntity.getTestAuthorsTo();
		List<AuthorTo> result = bookEntity.getAuthors();
		System.out.println(expected);
		// then
		assertEquals(bookTo.getId(),bookEntity.getId());
		assertEquals(bookTo.getTitle(), bookEntity.getTitle());
		assertThat(result, is(expected));
	}

}
