package com.Talan.history.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Talan.history.entities.History;

@Repository
public interface HistoryRepository extends MongoRepository<History, String>{
	
}

