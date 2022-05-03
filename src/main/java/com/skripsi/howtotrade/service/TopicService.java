package com.skripsi.howtotrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skripsi.howtotrade.mapper.TopicMapper;
import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicMapper mapper;

    @Autowired
    private UserMapper userMapper;

    public TopicService(){
        
    }

    public List<Map<String,String>> getAllTopic(){
        return mapper.getAllTopics();
    }

    public HashMap<String, String> getTopicById(int id){
        return mapper.getTopicById(id);
    }

    public void insertTopic(Topic topic){
        mapper.insertTopic(topic);
    }

    public void deleteTopic(int userId, int topicId){
        mapper.deleteTopic(userId, topicId);
    }

    public void deleteTopicAdmin(int topicId){
        mapper.deleteTopicAdmin(topicId);
    }
    
    public List<HashMap<String, String>> getCommentOnTopic(int id){
        return mapper.getCommentOnTopic(id);
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

    public String getRole(String username){
        return userMapper.getRole(username);
    }

    public void blockMember(int userId){
        mapper.blockMember(userId);
    }
}
