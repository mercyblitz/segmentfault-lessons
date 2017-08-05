package com.segmentfault.springbootlesson7.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.segmentfault.springbootlesson7.entity.Description;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DescriptionTypeHandler implements TypeHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {

        try {
            StringWriter stringWriter = new StringWriter();
            objectMapper.writeValue(stringWriter, parameter);
            String desc = stringWriter.toString();
            ps.setString(i, desc);
        } catch (IOException e) {
            throw new SQLException(e);
        }

    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {

        String desc = rs.getString(columnName);

        Description description = null;

        try {
            if (StringUtils.hasText(desc)) {
                description = objectMapper.readValue(desc, Description.class);
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }


        return description;
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        String desc = rs.getString(columnIndex);

        Description description = null;

        try {
            if (StringUtils.hasText(desc)) {
                description = objectMapper.readValue(desc, Description.class);
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }


        return description;
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
