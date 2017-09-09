package com.segmentfault.springbootlesson18.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.09
 */
@ConfigurationProperties("book")
public class Book {

    private String isbn;

    private String name;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
