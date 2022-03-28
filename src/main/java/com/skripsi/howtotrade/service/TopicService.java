package com.skripsi.howtotrade.service;

import java.util.List;

import com.skripsi.howtotrade.mapper.TopicMapper;
import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicMapper mapper;

    @Autowired
    private UserMapper userMapper;

    Authentication authentication;
    //String userLogged = authentication.getName(); -- right now it still return null values

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

    public void insertTopic(Topic topic){
        mapper.insertTopic(topic);
    }

    public void deleteTopic(int userId, int topicId){
        mapper.deleteTopic(userId, topicId);
    }

    public List<Comment> getCommentOnTopic(int id){
        List<Comment> resultList = mapper.getCommentOnTopic(id);
        return resultList;
    }

    public int getUserId(String userName){
        return userMapper.getUserId(userName);
    }
    
    public void insertComment(Comment comment){
       
        mapper.insertComment(comment);
    }

    public void deleteComment(int commentId, int topicId){
        mapper.deleteComment(commentId, topicId);
    }
}
