package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

import pl.spring.demo.dao.impl.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.repository.BookSearchCriteriaRepository;

public class BookRepositoryImpl implements BookSearchCriteriaRepository {

	@PersistenceContext(name = "hsql")
	private EntityManager entityManager;

	public List<BookEntity> findBookBySearchCriteria(BookSearchCriteria sc) {
		QBookEntity book = QBookEntity.bookEntity;
		JPAQuery query = new JPAQuery(entityManager).from(book);
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (sc.getTitle() != null) {
			Predicate ifTitleMatches = book.title.startsWithIgnoreCase(sc.getTitle());
			booleanBuilder.and(ifTitleMatches);
		}
		if (sc.getAuthors() != null) {
			String[] authorNames = sc.getAuthors().split(" ");
			String firstName = authorNames[0];
			String lastName = authorNames[authorNames.length - 1];
			Predicate ifFirstNameMatches = book.authors.any().firstName.startsWithIgnoreCase(firstName);
			Predicate ifLastNameMatches = book.authors.any().lastName.startsWithIgnoreCase(lastName);
			booleanBuilder.andAnyOf(ifFirstNameMatches, ifLastNameMatches);
		}
		if(sc.getLibraryName()!=null){
			Predicate ifLibraryNameMatches = book.library.name.startsWithIgnoreCase(sc.getLibraryName());
			booleanBuilder.and(ifLibraryNameMatches);
		}
		return query.where(booleanBuilder).listResults(book).getResults();
	}

}
