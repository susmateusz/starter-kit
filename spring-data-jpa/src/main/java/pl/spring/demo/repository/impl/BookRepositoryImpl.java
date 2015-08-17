package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.spring.demo.dao.impl.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.repository.BookSearchCriteriaRepository;

public class BookRepositoryImpl implements BookSearchCriteriaRepository {

	@PersistenceContext(name="hsql")
	private EntityManager entityManager;
	@Override
	public List<BookEntity> findBookBySearchCriteria(BookSearchCriteria bookSearchCriteria) {
		return null;
	}

}
