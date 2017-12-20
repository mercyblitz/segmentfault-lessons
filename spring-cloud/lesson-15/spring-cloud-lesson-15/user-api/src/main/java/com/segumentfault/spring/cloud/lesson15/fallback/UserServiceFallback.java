package com.segumentfault.spring.cloud.lesson15.fallback;

import com.segumentfault.spring.cloud.lesson15.api.UserService;
import com.segumentfault.spring.cloud.lesson15.domain.User;

import java.util.Collections;
import java.util.List;

/**
 * {@link UserService} Fallback 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class UserServiceFallback implements UserService {

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }
}
