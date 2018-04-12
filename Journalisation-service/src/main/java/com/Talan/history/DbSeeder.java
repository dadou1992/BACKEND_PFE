package com.Talan.history;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Talan.history.entities.History;
import com.Talan.history.service.HistoryService;


@Component
public class DbSeeder implements CommandLineRunner{
	@Autowired
	private HistoryService historyService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*History h1 = new History("test", "test", "test", (long) 12, "test", "test", "ok");
		History h2 = new History("test2", "test2", "test2", (long) 1222, "test2", "test2", "ok");

		this.historyService.save(h1);
		this.historyService.save(h2);*/

	}

}
