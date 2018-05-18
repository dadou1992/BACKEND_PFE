package com.Talan.gestionUtilisateur.service;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.Talan.gestionUtilisateur.dao.BotoolPageRepository;
import com.Talan.gestionUtilisateur.entities.BotoolPage;
import com.Talan.gestionUtilisateur.model.History;

@Service
public class PageServiceImp implements PageService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BotoolPageRepository botoolPageRepository;

	@Override
	public List<BotoolPage> findAll() {
		// TODO Auto-generated method stub
		return botoolPageRepository.findAll();
	}

	@Override
	public BotoolPage findOne(Long id) {
		// TODO Auto-generated method stub
		return botoolPageRepository.findOne(id);
	}

	@Override
	public BotoolPage save(BotoolPage c) {
		// TODO Auto-generated method stub
		return  botoolPageRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		botoolPageRepository.delete(id);	
	}

	@Override
	public BotoolPage Update(BotoolPage p) {
		
		try {
			//update 
			History history = new History();
			history.setOperationName("Update "+p.getPageName()+" page");
			history.setOperationDescription("Configuration des écrans privilégiés");
			//date
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("OK");

			// IP
			InetAddress IP = null;
			try {
				IP = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			history.setUserIP(IP.toString());
			//commentaire
			history.setCommentaire(" ");
			
			try {
				restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"), history, null);
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return  botoolPageRepository.save(p);
			
		}catch (Exception exception) {
			
			//update 
			History history = new History();
			history.setOperationName("Update "+p.getPageName()+" page");
			history.setOperationDescription("Echec de configuration des écrans privilégiés");
			//date
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("KO");

			// IP
			InetAddress IP = null;
			try {
				IP = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			history.setUserIP(IP.toString());
			//commentaire
			history.setCommentaire(" ");
			
			try {
				restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"), history, null);
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
			
			
		}
		
		
	}	

}
