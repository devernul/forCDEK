package ru.sample2705.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import ru.sample2705.mvc.bean.User;


public interface JDBCControllerInterface {

    ModelAndView jdbcSelectAllUsers();


    ModelAndView jdbcSelectAllUsers(String queryString);


    String addUserWithModel(User user);
}
