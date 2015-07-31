package pl.spring.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.common.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
    private BookDao bookDao;
	
	@Autowired
	private BookMapper bookMapper;

    @Override
    public List<BookTo> findAllBooks() {
        return bookDao.findAll().stream().map(bookMapper::mapToBookTo).collect(Collectors.toList());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return bookDao.findBookByTitle(title).stream().map(bookMapper::mapToBookTo).collect(Collectors.toList());
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return bookDao.findBooksByAuthor(author).stream().map(bookMapper::mapToBookTo).collect(Collectors.toList());
    }

    @Override
    public BookTo saveBook(BookTo book) {
        return bookMapper.mapToBookTo(bookDao.save(bookMapper.mapToBookEntity(book)));
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
