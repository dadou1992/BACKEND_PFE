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
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.Talan.gestionUtilisateur.ClientAppConfiguration;
import com.Talan.gestionUtilisateur.dao.UserRepository;
import com.Talan.gestionUtilisateur.entities.BotoolUser;
import com.Talan.gestionUtilisateur.model.History;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<BotoolUser> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public BotoolUser findOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public BotoolUser save(BotoolUser u) {
		try {
			// update
			History history = new History();
			history.setOperationName("Add user");
			history.setOperationDescription("Ajout d'un nouvel utilisateur");
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
			history.setCommentaire(" ");

			try {
				this.restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"),
						history, null);
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return userRepository.save(u);

		} catch (Exception exception) {

			// update
			History history = new History();
			history.setOperationName("Add user");
			history.setOperationDescription("Echec d'ajout d'un nouvel utilisateur");
			// date
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("KO");

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
			history.setCommentaire(" ");

			try {
				restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"),
						history, null);
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			return null;

		}

	}

	@Override
	public void delete(Long id) {
		try {

			// update
			History history = new History();
			history.setOperationName("Delete user");
			history.setOperationDescription("Suppression d'un utilisateur");
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
			history.setCommentaire(" ");

			try {
				this.restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"),history, null);
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			userRepository.delete(id);

		} catch (Exception e) {

			// update
			History history = new History();
			history.setOperationName("Delete user");
			history.setOperationDescription("Echec de suppression d'un utilisateur");
			// date
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("KO");

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
			history.setCommentaire(" ");

			try {
				this.restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"),
						history, null);
			} catch (RestClientException e1) {
				e.printStackTrace();
			} catch (URISyntaxException e1) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public BotoolUser Update(BotoolUser u) {

		try {

			// update
			History history = new History();
			history.setOperationName("Update user profil");
			history.setOperationDescription("Mise à jour d'un utilisateur");
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
			history.setCommentaire(" ");

			try {
				this.restTemplate.postForEntity(new URI("http://journalisation-service/addOperation"),history, null);

			} catch (RestClientException e) {
				e.printStackTrace();
			} 
			
			return userRepository.save(u);

		} catch (Exception exception) {

			// update
			History history = new History();
			history.setOperationName("Update user profil");
			history.setOperationDescription("Echec de mise à jour d'un utilisateur");
			// date
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("KO");

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
			history.setCommentaire(" ");

			try {
				this.restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"),history, null);
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			return null;

		}

	}

}
