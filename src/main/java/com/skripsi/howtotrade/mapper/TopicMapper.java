package com.skripsi.howtotrade.mapper;

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

    @Select("SELECT T.TOPICID, T.TOPICTITLE, t.description, T.CREATEDDATE, T.AUTHORID, U.USERNAME, COUNT(C.COMMENTID) AS COUNTCOMMENT "
        + "FROM TOPIC T "
        + "LEFT JOIN USERS U ON T.AUTHORID = U.USERID "
        + "LEFT JOIN COMMENT C ON C.TOPICID = T.TOPICID "
        + "GROUP BY T.TOPICID, T.TOPICTITLE, t.description, T.CREATEDDATE, T.AUTHORID, U.USERNAME")
    List<Map<String,String>> getAllTopics();

    @Select("SELECT * FROM TOPIC WHERE topicId = #{id}")
    Topic getTopicById(int id);

    @Select("SELECT * FROM COMMENT C LEFT JOIN USERS U ON U.USERID = C.USERID WHERE topicId = #{id}")
    List<Comment> getCommentOnTopic(int id);

    

    @Insert("INSERT INTO comment(description, createddate, userid, topicid) VALUES (#{description}, CURRENT_DATE , #{userId}, #{topicId})")
    void insertComment(Comment newComment);

    @Delete("DELETE FROM comment WHERE commentid = #{commentId} AND topicid = #{topicId}")
    void deleteComment(@Param("commentId") int commentId, @Param("topicId") int topicId);

    @Insert("INSERT INTO topic(topictitle, description, createddate, authorid) VALUES (#{topicTitle}, #{description}, CURRENT_DATE, #{authorId})")
    void insertTopic(Topic newTopic);

    @Delete("DELETE FROM topic WHERE authorid = #{authorId} AND topicid = #{topicId}")
    void deleteTopic(@Param("authorId") int authorId, @Param("topicId") int topicId);

    @Select("SELECT COUNT(*) FROM TOPIC WHERE topicId = #{id}")
    int countComment(int topicId);

    @Update("UPDATE USERS SET USERSTATUS = '0' WHERE USERID = #{userId}")
    void blockMember(int userId);
}
