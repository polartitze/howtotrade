package com.skripsi.howtotrade.mapper;

import java.util.List;

import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;
import com.skripsi.howtotrade.model.Users;

import org.apache.ibatis.annotations.Delete;
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

    

    @Insert("INSERT INTO comment(description, createddate, userid, topicid) VALUES (#{description}, CURRENT_DATE , #{userId}, #{topicId})")
    void insertComment(Comment newComment);

    @Delete("DELETE FROM comment WHERE commentid = #{commentId} AND topicid = #{topicId}")
    void deleteComment(int commentId, int topicId);

    @Insert("INSERT INTO topic(topictitle, description, createddate, authorid) VALUES (#{topicTitle}, #{description}, CURRENT_DATE, #{authorId})")
    void insertTopic(Topic newTopic);

    @Delete("DELETE FROM topic WHERE authorid = #{authorId} AND topicid = #{topicId}")
    void deleteTopic(int authorId, int topicId);
}
