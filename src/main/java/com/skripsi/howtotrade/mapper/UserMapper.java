package com.skripsi.howtotrade.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skripsi.howtotrade.model.Users;

@Mapper
public interface UserMapper {
	@Select("SELECT 1 FROM USERS WHERE USERNAME = #{username}")
	String getUser(String username);
	
	@Select("SELECT 1 FROM USERS WHERE USERNAME = #{username} AND USERPASSWORD = #{password}")
	String getPassword(String username, String password);

	@Insert("INSERT INTO USERS (userName, userEmail, userPassword, userRole, userStatus) "
			+ "VALUES(#{userName}, #{userEmail}, #{userPassword}, #{userRole}, #{userStatus})")
	void insert(Users user);

	@Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
	Users findUserAccount(String username);

	@Select("SELECT ROLENAME FROM USERS U JOIN ROLES R ON U.USERROLE = R.ROLEID WHERE U.USERNAME = #{username}")
	String getRole(String username);

	@Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
	Users getUserProfile(String username);

	@Update("UPDATE USERS SET userName = #{userName}, userEmail = #{userEmail} WHERE USERID = #{userId}")
	void saveProfile(Users user);
	
	@Select("SELECT USERID FROM USERS WHERE USERNAME = #{userName}")
    int getUserId(String userName);

}
