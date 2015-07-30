package mapper;

import org.springframework.stereotype.Component;

import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.to.BookTo;

@Component("bookMapper")
public class BookMapper {

	public BookTo mapBookEntity(BookEntity bookEntity) {
		BookTo bookTo = null;
		System.out.println("Inside 1");
		if (bookEntity != null) {
			System.out.println("Inside 2");
			bookTo = new BookTo();
			bookTo.setId(bookEntity.getId());
			bookTo.setTitle(bookEntity.getTitle());
		}
		System.out.println("Inside 3");
		return null;

	}

	public BookEntity mapBookTo(BookTo bookTo) {
		return null;
	}
}
