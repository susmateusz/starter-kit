package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.entity.BookEntity;

public interface BookDao extends Dao<BookEntity, Long> {

    List<BookEntity> findBookByTitle(String title);
}
