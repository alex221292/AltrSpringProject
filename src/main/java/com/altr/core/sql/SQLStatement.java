package com.altr.core.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class SQLStatement {
    private static final Logger logger = LoggerFactory.getLogger(SQLStatement.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static final class GeneralMapper<T> implements RowMapper<T> {
        private T object;

        public GeneralMapper(T arg) {
            object = arg;
        }

        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Class inputClass = object.getClass();
                if (object instanceof Integer) {
                    Integer intResult = new Integer(rs.getInt(1));
                    return (T) intResult;
                } else if (object instanceof String) {
                    return (T) rs.getString(1);
                } else {
                    int count = rs.getMetaData().getColumnCount();
                    String[] arguments = new String[count];
                    for (int i = 0; i < count; i++) {
                        arguments[i] = rs.getString(i + 1);
                    }
                    Object[] args = {arguments};
                    Constructor constructor = inputClass.getConstructor(String[].class);
                    object = (T) constructor.newInstance(args);
                    return object;
                }


            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return object;
        }


    }

    @Transactional
    public String getStringBySQL(String query, Object[] values) {
        String result = "";
        Collection<String> results = this.jdbcTemplate.query(query,
                values, new GeneralMapper<String>(new String()));
        for (String r : results) {
            result = r;
        }
        if (result == null) {
            result = "null";
        }
        return result;
    }

    @Transactional
    public int getIntBySQL(String query, Object[] values) {
        Integer result = new Integer(0);
        Collection<Integer> results = this.jdbcTemplate.query(query,
                values, new GeneralMapper<Integer>(new Integer(0)));
        for (Integer r : results) {
            result = r;
        }
        return result;
    }

    @Transactional
    public Collection<Integer> getIntListBySQL(String query, Object[] values) {
        Collection<Integer> results = this.jdbcTemplate.query(query,
                values, new GeneralMapper<Integer>(new Integer(0)));
        return results;
    }

    @Transactional
    public Collection<String> getStringListBySQL(String query, Object[] values) {
        Collection<String> results = this.jdbcTemplate.query(query,
                values, new GeneralMapper<String>(new String()));
        return results;
    }

    public void init()
    {

    }

    public void destroy()
    {

    }

}
