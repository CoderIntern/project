package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    //注入service依赖，@Autowired 自动注入
    @Autowired
    private UserDao userDao;
    public List<User> queryAllUser(){
        return userDao.queryAllUser();
    }
}
