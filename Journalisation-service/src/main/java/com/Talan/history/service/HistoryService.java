package com.Talan.history.service;

import java.util.List;

import com.Talan.history.entities.History;

public interface HistoryService {
	
	public History findOne(String id);
	public List<History> findAll();
	public History save(History c);

}
