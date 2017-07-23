package com.segmentfault.springbootlesson8.repository;

import com.segmentfault.springbootlesson8.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * 客户仓储
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.07.23
 */
@Repository
@Transactional(readOnly = false)
public class CustomerRepository extends SimpleJpaRepository<Customer, Long> {

    @Autowired
    public CustomerRepository(EntityManager em) {
        super(Customer.class, em);
    }

}
