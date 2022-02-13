package com.skripsi.howtotrade.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.skripsi.howtotrade.model.Users;

@Mapper
public interface UserMapper {
	@Select("SELECT 1 FROM USERS WHERE username = #{username}")
	String getUser(String username);
	
	@Insert("INSERT INTO USERS (userName, userEmail, userPassword, userRole, userStatus) "
			+ "VALUES(#{userName}, #{userEmail}, #{userPassword}, #{userRole}, #{userStatus})")
	void insert(Users user);
}
