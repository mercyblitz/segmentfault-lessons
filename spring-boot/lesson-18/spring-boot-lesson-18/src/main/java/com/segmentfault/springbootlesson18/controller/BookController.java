package com.segmentfault.springbootlesson18.controller;

import com.segmentfault.springbootlesson18.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.09
 */
@RestController
@EnableConfigurationProperties(Book.class)
public class BookController {

    @Autowired
    private Book book;

    @GetMapping("/book")
    public Book book() {
        return book;
    }
}
