package com.Talan.history;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Talan.history.entities.History;
import com.Talan.history.service.HistoryService;
import com.Talan.history.service.HistoryServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JournalisationServiceApplicationTests.class)
@EnableAutoConfiguration
public class JournalisationServiceApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(JournalisationServiceApplicationTests.class);

	@Autowired(required = true)
	private HistoryService historyService;

	@TestConfiguration
	static class HistoryServiceImplTestContextConfiguration {

		@Bean
		public HistoryService historyService() {
			return new HistoryServiceImp();
		}
	}

	@Test
	public void findOperationByPrimaryKey() {
		LOGGER.info("Début d'exécution de test unitaire de méthode findOperationByPrimaryKey ");

		History foundHistory = historyService.findOne("5ac5f6951d9b4e1c7ca48aa3");
		LOGGER.info("Résultat : " + foundHistory.toString());
		assertNotNull(foundHistory);
		assertThat(foundHistory.getOperationDescription()).isEqualTo("Authentification de l'utilisateur abir");
		LOGGER.info("Fin d'exécution de test unitaire de méthode GetAllOperationsTest");

	}

	// @Test
	public void GetAllOperationsTest() {
		LOGGER.info("Début d'exécution de test unitaire de méthode GetAllOperationsTest ");
		List<History> result = historyService.findAll();
		assertNotNull(result);
		result.forEach(i -> LOGGER.info("Résultats : " + i.toString()));
		LOGGER.info("Fin d'exécution de test unitaire de méthode GetAllOperationsTest ");
	}

}
