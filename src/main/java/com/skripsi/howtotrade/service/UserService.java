package com.skripsi.howtotrade.service;

import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Users findUserAccount(String username){
        return userMapper.findUserAccount(username);
    }
    
    public void saveProfile(String userEmail, String userPassword, int userId){
        userMapper.saveProfile(userEmail, userPassword, userId);
    }

    public Integer getUserId(String userName){
        return userMapper.getUserId(userName);
    }
    
    public String getUserRole(String userName) {
    	return userMapper.getRole(userName);
    }
    
    public String getUserName(int userId){
        return userMapper.getUserName(userId);
    }
    
    public String getOldPassword(String userName){
        return userMapper.getOldPassword(userName);
    }

    public void changeProfile(String userImage, int userId){
        userMapper.changeProfile(userImage, userId);
    }
    
    public List<Users> getAllMember(){
    	return userMapper.getAllMember();
    }
    
    public boolean blockUser(int userId) {
    	try {
    		userMapper.blockUser(userId);    		
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
			return false;
		}
    }
    
    public boolean unblockUser(int userId) {
    	try {
    		userMapper.unblockUser(userId);    		
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
			return false;
		}
    }
    
    public boolean changetoExpert(int userId) {
    	try {
    		userMapper.changetoExpert(userId);    		
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
			return false;
		}
    }
    
}
