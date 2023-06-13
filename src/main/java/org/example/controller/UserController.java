package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("User")
@Transactional
public class UserController {
    @Autowired
    UserService userService;

    //添加
    @RequestMapping("/createUser")
    public String createUser(User user) {
        User user1 =  userService.createUser(user);
        return "插入成功" + user1.toString();
    }


    //查询所有
    @RequestMapping("/findUsers")
    public List<User> findUsers() {
        List<User> Users = userService.findUsers();
        return Users;
    }

    //条件查询
    @RequestMapping("/findUserById")
    public User findUserById(String userId) {
        User user =  userService.findUserById(userId);
        return user;
    }

    //分页查询
    @RequestMapping("/findUsersPage")
    public Map<String, Object> findUserById() {
        Map<String, Object> pageMap =  userService.findUsersPage();
        return pageMap;
    }


    //模糊查询
    @RequestMapping("/findUsersLikeName")
    public List<User> findUsersLikeName(User userInfo) {
        List<User> users =  userService.findUsersLikeName(userInfo);
        return users;
    }


    //修改
    @RequestMapping("/updateUser")
    public String updateUser(User userInfo) {
        long count = userService.updateUser(userInfo);
        return count + "条数据修改成功";
    }

    //删除操作
    @RequestMapping("/deleteUser")
    public String deleteUser(String userId) {
        long count = userService.deleteUser(userId);
        return count + "条数据删除成功";
    }


}
