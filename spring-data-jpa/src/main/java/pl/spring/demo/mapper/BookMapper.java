package pl.spring.demo.mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

@Component("bookMapper")
public class BookMapper {

	public BookTo mapToBookTo(BookEntity bookEntity) {
		BookTo bookTo = null;
		if (bookEntity != null) {
			bookTo = new BookTo();
			bookTo.setId(bookEntity.getId());
			bookTo.setTitle(bookEntity.getTitle());
			bookTo.setAuthors(
					bookEntity.getAuthors().stream().map(AuthorTo::toString).collect(Collectors.joining(", ")));
		}
		return bookTo;

	}

	public BookEntity mapToBookEntity(BookTo bookTo) {
		BookEntity bookEntity = null;
		if (bookTo != null) {
			bookEntity = new BookEntity();
			bookEntity.setId(bookTo.getId());
			bookEntity.setTitle(bookTo.getTitle());
			Arrays.stream(bookTo.getAuthors().split(", ")).map(s -> s.split(" "))..forEach(System.out::println);
//			bookEntity.setAuthors(Arrays.stream(bookTo.getAuthors().split(", "))
//					.map(i -> new AuthorTo(Long.parseLong(i.split(" ")[0]), i.split(" ")[1], i.split(" ")[2]))
//					.collect(Collectors.toList()));
		}
		return bookEntity;
	}
}
