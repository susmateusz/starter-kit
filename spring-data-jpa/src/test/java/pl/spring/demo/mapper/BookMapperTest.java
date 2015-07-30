package pl.spring.demo.mapper;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mapper.BookMapper;
import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookMapperTest {
	
	@Autowired
	private BookMapper bookMapper;

	@Test
	public void testShouldReturnNullWhenNullBookEntity(){
		// given
		BookEntity bookEntity = null;
		// when
		System.out.println("OUTSIDE 1");
		BookTo bookTo = bookMapper.mapBookEntity(bookEntity);
		System.out.println("OUTSIDE 2");
		// then
		assertNull(bookTo);
		
	}

}
