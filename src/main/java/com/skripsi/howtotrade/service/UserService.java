package com.skripsi.howtotrade.service;

import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Users getUserProfile(String username){
        System.out.println("Get User Data : "+userMapper.getUserProfile(username));
        return userMapper.getUserProfile(username);
    }
    
    public void saveProfile(Users user){
        userMapper.saveProfile(user);
    }
}
