package com.segmentfault.springbootlesson8.entity.listener;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

/**
 * 客户持久化监听器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.07.23
 */
public class CustomerListener {

    @PrePersist
    public void prePersist(Object source) {

        System.out.println("@PrePersist : " + source);

    }

    @PostPersist
    public void postPersist(Object source) {

        System.out.println("@PostPersist : " + source);

    }


}
