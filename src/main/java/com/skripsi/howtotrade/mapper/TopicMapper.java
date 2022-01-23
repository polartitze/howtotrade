package com.skripsi.howtotrade.mapper;

import java.util.List;

import com.skripsi.howtotrade.model.Topic;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface TopicMapper {
    @Select("SELECT * FROM TOPIC T LEFT JOIN \"USER\" U ON T.AUTHORID = U.USERID")
    List<Topic> getAllTopics();

    @Select("SELECT * FROM TOPIC WHERE topicId = #{id}")
    Topic getTopicById(int id);
}
