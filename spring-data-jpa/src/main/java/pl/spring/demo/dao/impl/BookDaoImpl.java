package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.BookMapper;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.to.AuthorTo;

@Service("target")
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;
	
	@Autowired
	private BookMapper bookMapper;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		Predicate<BookEntity> notNullTitle = b -> b.getTitle() != null;
		Predicate<BookEntity> titleStartsWith = notNullTitle.and(b -> b.getTitle().toLowerCase().startsWith(title.toLowerCase()));
		return ALL_BOOKS.stream().filter(titleStartsWith).collect(Collectors.toList());
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		Predicate<String> isSubstring = s-> s.toLowerCase().indexOf(author.toLowerCase())>=0;
		Predicate<BookEntity> isAuthorPresentInList = b->b.getAuthors().stream().map(Object::toString).anyMatch(isSubstring);
		return ALL_BOOKS.stream().filter(isAuthorPresentInList).collect(Collectors.toList());
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	private void addTestBooks() {
		List<AuthorTo> testAuthors = BookEntity.getTestAuthorsTo();
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", testAuthors.subList(0, 1)));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", testAuthors.subList(1, 2)));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", testAuthors.subList(2, 3)));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", testAuthors.subList(3, 4)));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", testAuthors.subList(4, 5)));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", testAuthors.subList(5, 6)));
	}
}
