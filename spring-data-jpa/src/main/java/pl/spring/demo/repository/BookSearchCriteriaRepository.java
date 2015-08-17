package pl.spring.demo.repository;

import java.util.List;

import pl.spring.demo.dao.impl.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

public interface BookSearchCriteriaRepository {

	List<BookEntity> findBookBySearchCriteria(BookSearchCriteria bookSearchCriteria);
}
