package com.skripsi.howtotrade.service;

import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Users findUserAccount(String username){
        return userMapper.findUserAccount(username);
    }
    
    public void saveProfile(String userEmail, int userId){
        userMapper.saveProfile(userEmail, userId);
    }
    
    public void saveProfileWithPassword(String userEmail, String userPassword, int userId){
        userMapper.saveProfileWithPassword(userEmail, userPassword, userId);
    }

    public int getUserId(String userName){
        return userMapper.getUserId(userName);
    }

    public String getOldPassword(String userName){
        return userMapper.getOldPassword(userName);
    }

    public void changeProfile(String imagePath, int userId){
        userMapper.changeProfile(imagePath, userId);
    }
}
