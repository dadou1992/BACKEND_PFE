package com.Talan.history.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.Talan.history.dao.HistoryRepository;
import com.Talan.history.entities.History;

@Service
public class HistoryServiceImp implements HistoryService {

	@Autowired(required=true)
	@Qualifier("historyRepository")
	private HistoryRepository historyRepository;
	
	@Override
	public History findOne(String id) {
		System.out.println("tttt");
		return historyRepository.findOne(id);
	}
	
	@Override
	public List<History> findAll() {
		return historyRepository.findAll();
	}

	@Override
	public History save(History c) {
		return historyRepository.save(c);
	}

}
