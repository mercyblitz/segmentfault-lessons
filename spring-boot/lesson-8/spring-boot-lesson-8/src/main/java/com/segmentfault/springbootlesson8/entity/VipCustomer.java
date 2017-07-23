package com.segmentfault.springbootlesson8.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.07.23
 */
@Inheritance
@Entity
public class VipCustomer extends Customer {

    private Double discount;

    public VipCustomer(Double discount) {
        this.discount = discount;
    }
}
