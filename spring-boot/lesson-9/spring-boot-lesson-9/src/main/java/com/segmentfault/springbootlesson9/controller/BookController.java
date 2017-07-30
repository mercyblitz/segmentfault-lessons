package com.segmentfault.springbootlesson9.controller;

import com.segmentfault.springbootlesson9.entity.Book;
import com.segmentfault.springbootlesson9.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 图书的 REST 控制器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.07.30
 */
@RestController
public class BookController {

    @Autowired
    @Qualifier("bookRepository")
    private PagingAndSortingRepository<Book, String> bookRepository;

    @Autowired
    @Qualifier("bookRepository2")
    private BookRepository bookRepository2;

    @GetMapping(value = "/books/{name}")
    public List<Book> getBooks(@PathVariable String name) {
        return bookRepository2.findByName(name);
    }

    @GetMapping(value = "/book/{id}")
    public Book getBook(@PathVariable String id) {

        Book book = bookRepository2.findOne(id);

        return book;
    }

    @PostMapping(value = "/book/{id}")
    public Book publishBook(@PathVariable String id, @RequestBody Book book) {

        book.setId(id);
        book.setPublishedDate(new Date(System.currentTimeMillis()));

        bookRepository.save(book);

        return book;

    }

}
