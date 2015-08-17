package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.BookSearchService;
import pl.spring.demo.to.BookTo;

@Service
@Transactional(readOnly = true)
public class BookSearchServiceImpl implements BookSearchService {
	
	@Autowired
	private LibraryRepository LibraryRepository;

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookTo> findBooksByLibraryName(String libraryName) {
		// TODO Auto-generated method stub
		return null;
	}

}
