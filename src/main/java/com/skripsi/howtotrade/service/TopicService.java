package com.skripsi.howtotrade.service;

import java.util.List;

import com.skripsi.howtotrade.mapper.TopicMapper;
import com.skripsi.howtotrade.model.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicMapper mapper;

    
    // public void setMapper (TopicMapper mapper){
    //     this.mapper = mapper;
    // }

    public TopicService(){
        
    }

    public List<Topic> getAllTopic(){
        
        List<Topic> resultList = mapper.getAllTopics();

        return resultList;
    }
}
