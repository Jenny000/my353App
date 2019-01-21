package com.wang.inis.userLogin.repository;

import com.wang.inis.userLogin.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {

    UserLoginEntity save(UserLoginEntity userLoginEntity);
}
