package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("target")
public class BookDaoImpl implements BookDao {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

    @Autowired
    private Sequence sequence;

    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookEntity> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
        return null;
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
        return null;
    }

    @Override
    @NullableId
    public BookEntity save(BookEntity book) {
//        if (book.getId() == null) {
//        	System.out.println("Id null");
//            book.setId(sequence.nextValue(ALL_BOOKS));
//        }
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
        ALL_BOOKS.add(new BookEntity(6L, "Zemsta",  testAuthors.subList(5, 6)));
    }
}
