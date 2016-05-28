package ru.sample2705.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.sample2705.mvc.bean.User;
import ru.sample2705.mvc.jdbc.JDBCExample;

import java.util.List;

@Controller
public class JDBCController implements JDBCControllerInterface {

    @Autowired
    JDBCExample jdbcExample;

    @Autowired
    User user;

    @Override
    @RequestMapping(value = "/jdbcQueryAllUsers", method = RequestMethod.GET)
    public ModelAndView jdbcSelectAllUsers() {
        List<User> users =  jdbcExample.queryAllUsers();
        ModelAndView modelAndView = new ModelAndView("/jdbc/person", "resultObject", users);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/jdbcSearchByName", method = RequestMethod.GET)
    public ModelAndView jdbcSelectAllUsers(@RequestParam String query) {
        List<User> users =  jdbcExample.simpleFindUsers(query);
        ModelAndView modelAndView = new ModelAndView("/jdbc/person", "resultObject", users);
        modelAndView.addObject("user",user);
        return modelAndView;
    }


    @RequestMapping(value = "/jdbcInsert", method = RequestMethod.POST)
    public String addUserWithModel(@ModelAttribute("user")  User user) {
        jdbcExample.addUser(user);
        JDBCExample.logger.info("Add user with NAME = "+user.getUsername());
        return "redirect:/jdbcQueryAllUsers";
    }
}
