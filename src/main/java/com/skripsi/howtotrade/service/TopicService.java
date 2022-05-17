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
    private TopicMapper topicMapper;

    public TopicService(){
        
    }

    public List<Map<String,String>> getAllTopic(){
        return topicMapper.getAllTopics();
    }

    public HashMap<String, String> getTopicById(int id){
        return topicMapper.getTopicById(id);
    }

    public void insertTopic(Topic topic){
        topicMapper.insertTopic(topic);
    }

    public void deleteTopic(int userId, int topicId){
        topicMapper.deleteTopic(userId, topicId);
    }

    public void deleteTopicAdmin(int topicId){
        topicMapper.deleteTopicAdmin(topicId);
    }
    
    public List<HashMap<String, String>> getCommentOnTopic(int id){
        return topicMapper.getCommentOnTopic(id);
    }

    public void insertComment(Comment comment){
       
        topicMapper.insertComment(comment);
    }

    public void deleteComment(int commentId, int topicId){
        topicMapper.deleteComment(commentId, topicId);
    }

    public void blockMember(int userId){
        topicMapper.blockMember(userId);
    }

    public int getAuthor(int topicId){
        return topicMapper.getAuthor(topicId);
    }

    
}
