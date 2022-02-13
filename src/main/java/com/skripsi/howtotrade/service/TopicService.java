package com.skripsi.howtotrade.service;

import java.util.List;

import com.skripsi.howtotrade.mapper.TopicMapper;
import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicMapper mapper;

  

    public TopicService(){
        
    }

    public List<Topic> getAllTopic(){
        List<Topic> resultList = mapper.getAllTopics();
        return resultList;
    }

    public Topic getTopicById(int id){
        Topic resultList = mapper.getTopicById(id);
        return resultList;
    }

    public List<Comment> getCommentOnTopic(int id){
        List<Comment> resultList = mapper.getCommentOnTopic(id);
        return resultList;
    }

    public int getUserId(String userName){
        return mapper.getUserId(userName);
    }
    
    public void insertComment(Comment comment){
        mapper.insertComment(comment);
    }
}
