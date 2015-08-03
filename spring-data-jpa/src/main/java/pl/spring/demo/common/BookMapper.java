package pl.spring.demo.common;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
			if (bookEntity.getAuthors() != null)
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
			if (bookTo.getAuthors() != null)
				bookEntity.setAuthors(mapToAuthorTo(bookTo.getAuthors()));
		}
		return bookEntity;
	}

	public List<AuthorTo> mapToAuthorTo(String author){
		Function<String, String[]> splitter = s -> s.split(" ");
		Function<String, AuthorTo> authorToParse = splitter.andThen(i -> new AuthorTo(null, i[0], i[1]));
		return Arrays.stream(author.split(", ")).map(authorToParse).collect(Collectors.toList());
	}
}
