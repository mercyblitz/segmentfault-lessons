package com.segmentfault.springbootlesson6.domain;


import com.segmentfault.springbootlesson6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.PreparedStatement;
import java.sql.SQLException;


@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Override
    @Transactional
    public boolean save(User user) {


        Boolean result = jdbcTemplate.execute("INSERT INTO user(name,age) VALUES(?,?);", new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
                return ps.executeUpdate() > 0;
            }

        });

        return result;
    }

    @Transactional
    public boolean save2(User user) {
        Boolean result = false;
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            result = save(user);
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
        }

        return result;
    }


}
