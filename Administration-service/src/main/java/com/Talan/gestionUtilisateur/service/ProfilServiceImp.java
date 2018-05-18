package com.Talan.gestionUtilisateur.service;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.Talan.gestionUtilisateur.ClientAppConfiguration;
import com.Talan.gestionUtilisateur.dao.ProfilRespository;
import com.Talan.gestionUtilisateur.entities.Profils;
import com.Talan.gestionUtilisateur.model.History;

@Service
@Transactional
public class ProfilServiceImp implements ProfilService {
	@Autowired
	private ProfilRespository profilRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public List<Profils> findAll() {
		// TODO Auto-generated method stub
		return profilRepository.findAll();
	}

	@Override
	public Profils findOne(Long id) {
		// TODO Auto-generated method stub
		return profilRepository.findOne(id);
	}

	@Override
	public Profils save(Profils p) {
		// update
		History history = new History();
		history.setOperationName("Add profil");
		history.setOperationDescription("Ajout d'un nouveau profil");
		history.setStatus("OK");
		// date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		history.setOperationDate(dateFormat.format(date));
		history.setStatus("OK");

		// IP
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		history.setUserIP(IP.toString());
		// commentaire
		history.setCommentaire("");

		try {

			restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"), history,
					null);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return profilRepository.save(p);
	}

	

	
	
	@Override
	public void delete(Long id) {
		// update
		History history = new History();
		history.setOperationName("Delete profil");
		history.setOperationDescription("Suppression d'un profil");
		// date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		history.setOperationDate(dateFormat.format(date));
		history.setStatus("OK");

		// IP
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		history.setUserIP(IP.toString());
		// commentaire
		history.setCommentaire("");

		try {
			restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"), history,
					null);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		profilRepository.delete(id);

	}
	


	@Override
	public Profils Update(Profils p) {
		/// update
		History history = new History();
		history.setOperationName("Update profil");
		history.setOperationDescription("Mise à jour d'un profil");
		// date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		history.setOperationDate(dateFormat.format(date));
		history.setStatus("OK");

		// IP
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		history.setUserIP(IP.toString());
		// commentaire
		history.setCommentaire("");

		try {
			restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"), history,
					null);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return profilRepository.save(p);
	}

}
