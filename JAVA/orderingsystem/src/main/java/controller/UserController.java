package controller;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import java.util.List;

@Controller
public class UserController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/index")
//    @ResponseBody
    public String queryAllUser(Model model){
        List<User> users = userService.queryAllUser();
        System.out.print(userService.queryAllUser());
        for (User user:users
             ) {
            System.out.println(user.getName());
            System.out.println(user.getName());
        }
        model.addAttribute("users", users);
        return "index";
    }
}
