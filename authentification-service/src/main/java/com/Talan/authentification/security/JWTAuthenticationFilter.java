package com.Talan.authentification.security;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.Talan.authentification.entities.AppUser;
import com.Talan.authentification.model.History;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	@Autowired
	private RestTemplate restTemplate;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		AppUser appUser = null;
		try {
			appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		} catch (Exception e) {

			throw new RuntimeException(e);

		}

		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
	}

	@Autowired
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		log.info("Debut d'éxecution de la méthode successfulAuthentication");

		User springUser = (User) authResult.getPrincipal();

		String jwt = Jwts.builder().setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstant.SECRET).claim("roles", springUser.getAuthorities())
				.compact();

		response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + jwt);

		log.info("Fin d'éxecution de la méthode successfulAuthentication");
		
		
		History history = new History();
		
		try {
			history.setOperationName("Authentification");
			history.setOperationDescription(" Authentification de l'utilisateur " + springUser.getUsername());

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("OK");
			history.setCommentaire(" ");
			
			restTemplate = new RestTemplate();
			restTemplate.postForEntity(new URI("http://localhost:9999/journalisation-service/addOperation"),history, History.class);
		} catch (RestClientException e) {
			
			log.info("Echec d'insertion ");
			history.setOperationName("Authentification");
			history.setOperationDescription("Echec d'authentification");

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("KO");
			history.setCommentaire(" ");
			
	
			
		} catch (URISyntaxException e) {
			log.info("Echec d'insertion");
			
			history.setOperationName("Authentification");
			history.setOperationDescription("Echec d'authentification");

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			history.setOperationDate(dateFormat.format(date));
			history.setStatus("KO");
			history.setCommentaire(" ");
			
		}
	}
}
