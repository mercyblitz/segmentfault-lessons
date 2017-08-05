package com.segmentfault.springbootlesson9.repository;

import com.segmentfault.springbootlesson9.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图书 {@link BookRepository}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.07.30
 */
@Repository("bookRepository2")
public interface BookRepository extends PagingAndSortingRepository<Book, String> {

    List<Book> findByName(String name);

}
