package com.avikp.swipe.capture.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.avikp.swipe.capture.entity.SwipeInfo;

public interface SwipeInfoRepository extends MongoRepository<SwipeInfo, Integer>{

}
