package com.skripsi.howtotrade.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TopicMapper {

    @Select("SELECT T.TOPICID,  "
    + "	T.TOPICTITLE, "
    + "	t.description, "
    + "	CASE  "
    + "		WHEN (DATE_PART('day', CURRENT_TIMESTAMP - T.CREATEDDATE)) > 0 "
    + "			THEN CONCAT(DATE_PART('day', CURRENT_TIMESTAMP - T.CREATEDDATE), ' day ago') "
    + "        WHEN (DATE_PART('day', CURRENT_TIMESTAMP - T.CREATEDDATE)) < 1 "
    + "            THEN "
    + "                CASE "
    + "		            WHEN (DATE_PART('hour', CURRENT_TIMESTAMP - T.CREATEDDATE)) > 0 "
    + "			            THEN CONCAT(DATE_PART('hour', CURRENT_TIMESTAMP - T.CREATEDDATE), ' hour ago') "
    + "		            WHEN (DATE_PART('hour', CURRENT_TIMESTAMP - T.CREATEDDATE)) < 1 "
    + "                        THEN "
    + "                            CASE "
    + "                                WHEN (DATE_PART('minute', CURRENT_TIMESTAMP - T.CREATEDDATE)) > 0 "
    + "			                        THEN CONCAT(DATE_PART('minute', CURRENT_TIMESTAMP - T.CREATEDDATE),' minute ago') "
    + "                        		ELSE 'a few seconds ago' "
    + "                            END "
    + "                END "
    + "	END AS CREATEDDATE, "
    + "	T.AUTHORID, "
    + "	T.TOPICIMAGE, "
    + "	U.USERNAME,  "
    + "	COUNT(C.COMMENTID) AS COUNTCOMMENT  "
    + "FROM TOPIC T  "
    + "LEFT JOIN USERS U ON T.AUTHORID = U.USERID  "
    + "LEFT JOIN COMMENT C ON C.TOPICID = T.TOPICID  "
    + "GROUP BY T.TOPICID, T.TOPICTITLE, t.description, T.CREATEDDATE, T.AUTHORID, U.USERNAME "
    + "ORDER BY T.CREATEDDATE DESC")
    List<Map<String,String>> getAllTopics();

    @Select("SELECT T.*, "
    + "	U.USERNAME, "
    + "	TO_CHAR(T.CREATEDDATE,'DD Mon YYYY hh24:mi') AS POSTED , "
    + "		CASE  "
    + "		WHEN (DATE_PART('day', CURRENT_TIMESTAMP - T.CREATEDDATE)) > 0 "
    + "			THEN CONCAT(DATE_PART('day', CURRENT_TIMESTAMP - T.CREATEDDATE), ' day ago') "
    + "        WHEN (DATE_PART('day', CURRENT_TIMESTAMP - T.CREATEDDATE)) < 1 "
    + "            THEN "
    + "                CASE "
    + "		            WHEN (DATE_PART('hour', CURRENT_TIMESTAMP - T.CREATEDDATE)) > 0 "
    + "			            THEN CONCAT(DATE_PART('hour', CURRENT_TIMESTAMP - T.CREATEDDATE), ' hour ago') "
    + "		            WHEN (DATE_PART('hour', CURRENT_TIMESTAMP - T.CREATEDDATE)) < 1 "
    + "                        THEN "
    + "                            CASE "
    + "                                WHEN (DATE_PART('minute', CURRENT_TIMESTAMP - T.CREATEDDATE)) > 0 "
    + "			                        THEN CONCAT(DATE_PART('minute', CURRENT_TIMESTAMP - T.CREATEDDATE),' minute ago') "
    + "                        		ELSE 'a few seconds ago' "
    + "                            END "
    + "                END "
    + "	END AS COUNTDATE "
    + "FROM TOPIC T "
    + "LEFT JOIN USERS U ON T.AUTHORID = U.USERID "
    + "WHERE topicId = #{id}")
    HashMap<String,String> getTopicById(int id);

    @Select("SELECT C.*, U.*, TO_CHAR(C.CREATEDDATE,'DD Mon YYYY hh24:mi') AS POSTED FROM COMMENT C LEFT JOIN USERS U ON U.USERID = C.USERID WHERE C.topicId = #{id} "
    + "ORDER BY C.CREATEDDATE DESC")
    List<HashMap<String, String>> getCommentOnTopic(int id);

    @Insert("INSERT INTO comment(description, createddate, userid, topicid) VALUES (#{description}, CURRENT_TIMESTAMP , #{userId}, #{topicId})")
    void insertComment(Comment newComment);

    @Delete("DELETE FROM comment WHERE commentid = #{commentId} AND topicid = #{topicId}")
    void deleteComment(@Param("commentId") int commentId, @Param("topicId") int topicId);

    @Insert("INSERT INTO topic(topictitle, description, createddate, authorid, topicimage) VALUES (#{topicTitle}, #{description}, CURRENT_TIMESTAMP, #{authorId}, #{topicImage})")
    void insertTopic(Topic newTopic);

    @Delete("DELETE FROM topic WHERE authorid = #{authorId} AND topicid = #{topicId}")
    void deleteTopic(@Param("authorId") int authorId, @Param("topicId") int topicId);
    
    @Delete("DELETE FROM topic WHERE topicid = #{topicId}")
    void deleteTopicAdmin(@Param("topicId") int topicId);

    @Select("SELECT COUNT(*) FROM TOPIC WHERE topicId = #{id}")
    int countComment(int topicId);

    @Update("UPDATE USERS SET USERSTATUS = '0' WHERE USERID = #{userId}")
    void blockMember(int userId);

    @Select("SELECT AUTHORID FROM TOPIC WHERE TOPICID = #{topicId}")
    int getAuthor(int topicId);

}
