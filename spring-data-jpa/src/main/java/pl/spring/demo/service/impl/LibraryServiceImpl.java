package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository LibraryRepository;

	@Override
	public void removeLibraryById(long id) {
		LibraryRepository.delete(id);
	}

}
