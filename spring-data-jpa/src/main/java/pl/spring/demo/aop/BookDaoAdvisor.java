package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.enitities.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.IdAware;

@Service("bookDaoAdvisor")
public class BookDaoAdvisor implements MethodBeforeAdvice {

	@Autowired
	private Sequence sequence;

	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {
		if (hasAnnotation(method, o, NullableId.class)) {
			// not null - throws exception, null - pass and set new id
			checkNotNullId(objects[0]);
			setId(objects[0],o);
		}
	}

	private void checkNotNullId(Object o) {
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
		}
	}
	
	private void setId(Object book,Object bookDao){
		if (book instanceof BookEntity && bookDao instanceof BookDao)
			((BookEntity) book).setId(sequence.nextValue(((BookDao) bookDao).findAll()));
	}

	private boolean hasAnnotation(Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
		boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

		if (!hasAnnotation && o != null) {
			hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes())
					.getAnnotation(annotationClazz) != null;
		}
		return hasAnnotation;
	}
}
