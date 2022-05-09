package com.skripsi.howtotrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skripsi.howtotrade.model.Users;

@Mapper
public interface UserMapper {

	@Select("SELECT USERSTATUS FROM USERS WHERE USERNAME = #{username}")
	String getUser(String username);
	
	@Select("SELECT ISVERIFIED FROM USERS WHERE USERNAME = #{username}")
	String checkVerified(String username);

	@Select("SELECT USEREMAIL FROM USERS WHERE USEREMAIL = #{email}")
	String checkMail(String email);

	@Select("SELECT 1 FROM USERS WHERE USERNAME = #{username} AND USERPASSWORD = #{password}")
	String getPassword(String username, String password);

	@Insert("INSERT INTO USERS (userName, userEmail, userPassword, userRole, userStatus, userRealName) "
			+ "VALUES(#{userName}, #{userEmail}, #{userPassword}, #{userRole}, #{userStatus}, #{userRealName})")
	void insertUser(@Param("userName") String userName, @Param("userEmail")String userEmail, 
			@Param("userPassword")String userPassword, @Param("userRole")int userRole, @Param("userStatus")String userStatus,
			@Param("userRealName")String userRealName);

	@Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
	Users findUserAccount(String username);

	@Select("SELECT ROLENAME FROM USERS U JOIN ROLES R ON U.USERROLE = R.ROLEID WHERE U.USERNAME = #{username}")
	String getRole(String username);

	@Update("UPDATE USERS SET userEmail = #{userEmail}, userPassword = #{userPassword} WHERE USERID = #{userId}")
	void saveProfileWithPassword(@Param("userEmail") String userEmail, @Param("userPassword") String userPassword, 
			@Param("userId") int userId);
	
	@Update("UPDATE USERS SET userEmail = #{userEmail} WHERE USERID = #{userId}")
	void saveProfile(@Param("userEmail") String userEmail,	@Param("userId") int userId);

	@Select("SELECT USERID FROM USERS WHERE USERNAME = #{userName}")
    int getUserId(String userName);

	@Select("SELECT USERNAME FROM USERS WHERE USERID = #{userId}")
    String getUserName(int userId);
	
	@Select("SELECT USERPASSWORD FROM USERS WHERE USERNAME = #{userName}")
    String getOldPassword(String userName);

	@Update("UPDATE USERS SET imagePath = #{imagePath} WHERE USERID = #{userId}")
	void changeProfile(@Param("imagePath") String imagePath, @Param("userId") int userId);

	@Update("UPDATE USERS SET isVerified = '1' WHERE USERNAME = #{username}")
	void validateAccount(@Param("username") String username);

	@Select("SELECT * FROM users WHERE userrole = 1 and isVerified = '1' ORDER BY userid")
	List<Users> getAllMember();
	
	@Update("UPDATE users SET userstatus = '0' WHERE userid = #{userId}")
    void blockUser(int userId);
	
	@Update("UPDATE users SET userstatus = '1' WHERE userid = #{userId}")
    void unblockUser(int userId);
	
	@Update("UPDATE users SET userrole = '3' WHERE userid = #{userId}")
    void changetoExpert(int userId);
	
}
