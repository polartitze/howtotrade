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
	public String getUser(String username);
	
	@Select("SELECT ISVERIFIED FROM USERS WHERE USERNAME = #{username}")
	public String checkVerified(String username);

	@Select("SELECT USEREMAIL FROM USERS WHERE USEREMAIL = #{email}")
	public String checkMail(String email);

	@Select("SELECT 1 FROM USERS WHERE USERNAME = #{username} AND USERPASSWORD = #{password}")
	public String getPassword(String username, String password);

	@Insert("INSERT INTO USERS (userName, userEmail, userPassword, userRole, userStatus, userRealName) "
			+ "VALUES(#{userName}, #{userEmail}, #{userPassword}, #{userRole}, #{userStatus}, #{userRealName})")
	public void insertUser(@Param("userName") String userName, @Param("userEmail")String userEmail, 
			@Param("userPassword")String userPassword, @Param("userRole")int userRole, @Param("userStatus")String userStatus,
			@Param("userRealName")String userRealName);

	@Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
	public Users findUserAccount(String username);

	@Select("SELECT ROLENAME FROM USERS U JOIN ROLES R ON U.USERROLE = R.ROLEID WHERE U.USERNAME = #{username}")
	public String getRole(String username);

	@Update("UPDATE USERS SET userEmail = #{userEmail}, userPassword = #{userPassword} WHERE USERID = #{userId}")
	public void saveProfile(@Param("userEmail") String userEmail, @Param("userPassword") String userPassword, 
			@Param("userId") int userId);

	@Select("SELECT USERID FROM USERS WHERE USERNAME = #{userName}")
	public int getUserId(String userName);

	@Select("SELECT USERNAME FROM USERS WHERE USERID = #{userId}")
	public String getUserName(int userId);
	
	@Select("SELECT USERPASSWORD FROM USERS WHERE USERNAME = #{userName}")
	public String getOldPassword(String userName);

	@Update("UPDATE USERS SET userImage = #{userImage} WHERE USERID = #{userId}")
	public void changeProfile(@Param("userImage") String userImage, @Param("userId") int userId);

	@Update("UPDATE USERS SET isVerified = '1' WHERE USERNAME = #{username}")
	public void validateAccount(@Param("username") String username);

	@Select("SELECT * FROM users WHERE userrole = 1 and isVerified = '1' ORDER BY userid")
	public List<Users> getAllMember();
	
	@Update("UPDATE users SET userstatus = '0' WHERE userid = #{userId}")
	public void blockUser(int userId);
	
	@Update("UPDATE users SET userstatus = '1' WHERE userid = #{userId}")
	public void unblockUser(int userId);
	
	@Update("UPDATE users SET userrole = '3' WHERE userid = #{userId}")
	public void changetoExpert(int userId);
	
}
