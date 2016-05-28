package ru.sample2705.mvc.jdbc;

import ru.sample2705.mvc.bean.User;

import java.util.List;

public interface JDBCExampleInterface {
    void init();

    List<User> queryAllUsers();

    List<User> simpleFindUsers(String queryString);

    void addUser(User user);

}
