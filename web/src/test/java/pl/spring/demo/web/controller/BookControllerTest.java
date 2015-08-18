package pl.spring.demo.web.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class BookControllerTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(bookService);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testShouldFindAllBooks() throws Exception {
		// given
		final BookTo bookTo1 = new BookTo(1L, "Title 1", "Author1");
		final BookTo bookTo2 = new BookTo(2L, "Title 2", "Author2");
		final BookTo bookTo3 = new BookTo(3L, "Title 3", "Author3");
		final List<BookTo> bookToList = Arrays.asList(bookTo1, bookTo2, bookTo3);
		Mockito.when(bookService.findAllBooks()).thenReturn(bookToList);

		// when
		ResultActions response = mockMvc.perform(get("/books").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		// then
		Mockito.verify(bookService).findAllBooks();

		response.andExpect(status().isOk()).andExpect(view().name("bookList"))
				.andExpect(model().attributeExists("books"))
				.andExpect(model().attribute("books", containsInAnyOrder(bookTo1,bookTo2,bookTo3)));
	}
	
	@Test
	public void testShouldDeleteAndReturnBook() throws Exception {
		// given
		long bookId = 4L;
		String bookTitle = "Title 4";
		final BookTo bookTo = new BookTo(bookId, bookTitle, "Author4");
		Mockito.when(bookService.deleteBookById(new Long(bookId))).thenReturn(bookTo);
		
		// when
		ResultActions response = mockMvc.perform(get("/books/remove/4/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		// then
		Mockito.verify(bookService).deleteBookById(new Long(bookId));
		
		response.andExpect(status().isOk()).andExpect(view().name("bookDeleted"))
		.andExpect(model().attributeExists("deletedBookTitle"))
		.andExpect(model().attribute("deletedBookTitle", equalTo(bookTitle)));
	}

}
