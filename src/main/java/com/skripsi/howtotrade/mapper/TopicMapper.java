package com.skripsi.howtotrade.mapper;

import java.util.List;

import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;
import com.skripsi.howtotrade.model.Users;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TopicMapper {
    @Select("SELECT * FROM TOPIC T LEFT JOIN USERS U ON T.AUTHORID = U.USERID")
    List<Topic> getAllTopics();

    @Select("SELECT * FROM TOPIC WHERE topicId = #{id}")
    Topic getTopicById(int id);

    @Select("SELECT * FROM COMMENT C LEFT JOIN USERS U ON U.USERID = C.USERID WHERE topicId = #{id}")
    List<Comment> getCommentOnTopic(int id);

    @Select("SELECT USERID FROM USERS WHERE USERNAME = #{userName}")
    int getUserId(String userName);

    @Insert("INSERT INTO comment(description, createddate, userid, topicid) VALUES (#{description}, CURRENT_DATE , #{userId}, #{topicId})")
    void insertComment(Comment newComment);
}
