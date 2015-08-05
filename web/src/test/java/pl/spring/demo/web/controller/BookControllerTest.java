package pl.spring.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

public class BookControllerTest {
	
	@InjectMocks
	private BookController bookController;
	@Mock
	private BookService bookService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(bookController, "bookService", bookService);
	}

	@Test
	public void testBookList() throws Exception {
		BookController controller = new BookController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		Mockito.when(bookService.findAllBooks()).thenReturn(new ArrayList<BookTo>());
		mockMvc.perform(get("/books")).andExpect(view().name("bookList"));
	}

}
