package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.enitities.BookEntity;

public class BookDaoImplTest {
	
	@InjectMocks
	private BookDaoImpl bookDaoImpl;
	@Mock
	private Sequence sequence;
	private BookMapper bookMapper;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(bookDaoImpl, "sequence", sequence);
		bookMapper = new BookMapper();
	}

	@Test
	public void testShouldReturnBookWithoutChanges(){
		// given
		BookEntity bookEntity = new BookEntity(100L,"Lód",bookMapper.mapToAuthorTo("Jacek Dukaj"));
		// when
		BookEntity result = bookDaoImpl.save(bookEntity);
		// then
		assertEquals(bookEntity,result);
	}
}
