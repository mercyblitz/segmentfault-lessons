package com.segmentfault.springbootlesson9.repository;

import com.segmentfault.springbootlesson9.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.MappingElasticsearchEntityInformation;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Repository;

/**
 * 图书的仓储
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.07.30
 */
@Repository("bookRepository")
public class BookElasticsearchRepository extends AbstractElasticsearchRepository<Book, String> {

    private BookRepository bookRepository;

    public Book save(Book book){

        return bookRepository.save(book);

    }


    @Autowired
    public BookElasticsearchRepository(ElasticsearchOperations elasticsearchOperations) {
        super(createElasticsearchEntityInformation(), elasticsearchOperations);
        super.setEntityClass(Book.class);
    }

    private static ElasticsearchEntityInformation<Book, String> createElasticsearchEntityInformation() {

        TypeInformation<Book> typeInformation = ClassTypeInformation.from(Book.class);

        ElasticsearchPersistentEntity<Book> entity = new SimpleElasticsearchPersistentEntity(typeInformation);

        ElasticsearchEntityInformation<Book, String> information = new MappingElasticsearchEntityInformation(entity);

        return information;

    }


    @Override
    protected String stringIdRepresentation(String s) {
        return s;
    }

}
