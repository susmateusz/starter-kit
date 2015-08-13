package pl.spring.demo.service;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.mapper.BookMapper;

public interface LibraryService {

	public void removeLibraryById(long id);

}
