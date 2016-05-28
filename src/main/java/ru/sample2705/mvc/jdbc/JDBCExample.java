package ru.sample2705.mvc.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.sample2705.mvc.bean.User;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class JDBCExample implements JDBCExampleInterface {

    public static final Logger logger = LoggerFactory.getLogger(JDBCExample.class);

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;


    @Override
    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("user").usingGeneratedKeyColumns("id");
    }

    @Override
    public List<User> queryAllUsers() {

        final String QUERY_SQL = "SELECT * FROM user AS u ORDER BY IDUSER";
        List<User> userList = getUsersFromMysql(QUERY_SQL);
        return userList;
    }

    @Override
    public List<User> simpleFindUsers(String queryString) {

        final String QUERY_SQL = "SELECT * FROM user AS u WHERE u.USERNAME LIKE '%"+queryString+"%' ORDER BY IDUSER";
        List<User> userList = getUsersFromMysql(QUERY_SQL);
        return userList;
    }

    @Override
    public void addUser(User user) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("USERNAME", user.getUsername());
        simpleJdbcInsert.execute(parameters);
    }

    private List<User> getUsersFromMysql(String QUERY_SQL) {
        return this.jdbcTemplate.query(QUERY_SQL, new RowMapper<User>() {
            public User mapRow(ResultSet resulSet, int rowNum) throws SQLException {
                User user = new User();
                user.setIdUser(resulSet.getInt("IDUSER"));
                user.setUsername(resulSet.getString("USERNAME"));
                return user;
            }
        });
    }

}
