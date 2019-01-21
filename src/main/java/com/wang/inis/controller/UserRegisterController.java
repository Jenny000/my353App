package com.wang.inis.controller;

import com.wang.inis.userLogin.entity.UserLoginEntity;
import com.wang.inis.userLogin.repository.IUserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {

    @Autowired
    private IUserLogin userLogin;

    @RequestMapping(value = "/saveUser")
    @CrossOrigin(origins = "*")
    public void saveUser(UserLoginEntity userLoginEntity){
        System.out.println(userLoginEntity.toString());
        userLogin.save(userLoginEntity);
    }

}
