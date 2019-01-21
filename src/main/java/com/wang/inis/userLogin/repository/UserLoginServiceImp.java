package com.wang.inis.userLogin.repository;

import com.wang.inis.userLogin.entity.UserLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImp implements IUserLogin {
    @Autowired
    private UserLoginRepository userLogin;

    @Override
    public UserLoginEntity save(UserLoginEntity userLoginEntity) {
        return userLogin.save(userLoginEntity);
    }
}
