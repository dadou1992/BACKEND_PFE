package com.Talan.gestionUtilisateur;

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

import com.Talan.gestionUtilisateur.dao.BotoolPageRepository;
import com.Talan.gestionUtilisateur.dao.ProfilRespository;
import com.Talan.gestionUtilisateur.dao.UserRepository;
import com.Talan.gestionUtilisateur.entities.BotoolMenu;
import com.Talan.gestionUtilisateur.entities.BotoolPage;
import com.Talan.gestionUtilisateur.entities.BotoolUser;
import com.Talan.gestionUtilisateur.entities.Profils;
import com.Talan.gestionUtilisateur.service.MenuService;
import com.Talan.gestionUtilisateur.service.MenuServiceImp;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MicroServiceGestionUtilisateursApplicationTests.class)
@EnableAutoConfiguration
public class MicroServiceGestionUtilisateursApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceGestionUtilisateursApplicationTests.class);

	@Autowired(required = true)
	private MenuService menuService;
	
	@Autowired(required = true)
	private BotoolPageRepository pageService;
	
	@Autowired(required = true)
	private UserRepository userRepository;
	
	@Autowired(required = true)
	private ProfilRespository profilRepository;
		

	@TestConfiguration
	static class MenuServiceImplTestContextConfiguration {

		@Bean
		public MenuService menuService() {
			return new MenuServiceImp();
		}		
	}
	
	/*************************MenuTest*****************************/

    //@Test
	public void findMenuByPrimaryKeyTest() {
		LOGGER.info("Début d'exécution de test unitaire de méthode findMenuByPrimaryKeyTest ");
		BotoolMenu foundMenu = menuService.findOne((long) 1);
		LOGGER.info("Résultat : " + foundMenu.toString());
		assertNotNull(foundMenu);
		assertThat(foundMenu.getMenuName()).isEqualTo("Gestion des ressources");
		LOGGER.info("Fin d'exécution de test unitaire de méthode findMenuByPrimaryKeyTest");

	}

	@Test
	public void GetAllMenusTest() {
		LOGGER.info("Début d'exécution de test unitaire de méthode GetAllMenusTest ");
		List<BotoolMenu> result = menuService.findAll();
		assertNotNull(result);
		result.forEach(i -> LOGGER.info("Résultats : " + i.toString()));
		LOGGER.info("Fin d'exécution de test unitaire de méthode GetAllMenusTest ");
	}

	@Test
	public void saveMenuTest() {
		LOGGER.info("Début d'exécution de test unitaire de méthode saveMenuTest ");
		BotoolMenu menu = new BotoolMenu();
		menu.setMenuName("menuTest1");
		menuService.save(menu);
		assertNotNull(menu);
		LOGGER.info("Fin d'exécution de test unitaire de méthode saveMenuTest ");

	}
	
	/*************************PageTest*****************************/
	
	 //@Test
		public void findPageByPrimaryKeyTest() {
			LOGGER.info("Début d'exécution de test unitaire de méthode findPageByPrimaryKeyTest");
			BotoolPage foundPage = pageService.findOne((long) 1);
			LOGGER.info("Résultat : " + foundPage.toString());
			assertNotNull(foundPage);
			assertThat(foundPage.getPageName()).isEqualTo("Configuration des écrans");
			LOGGER.info("Fin d'exécution de test unitaire de méthode findPageByPrimaryKeyTest");

		}

		@Test
		public void GetAllPagesTest() {
			LOGGER.info("Début d'exécution de test unitaire de méthode GetAllPagesTest ");
			List<BotoolPage> result = pageService.findAll();
			assertNotNull(result);
			result.forEach(i -> LOGGER.info("Résultats : " + i.toString()));
			LOGGER.info("Fin d'exécution de test unitaire de méthode GetAllPagesTest ");
		}

		@Test
		public void savePageTest() {
			LOGGER.info("Début d'exécution de test unitaire de méthode savePageTest ");
			BotoolPage page = new BotoolPage();
			page.setPageName("pageTest 1");
			pageService.save(page);
			assertNotNull(page);
			LOGGER.info("Fin d'exécution de test unitaire de méthode savePageTest ");

		}
		
		/*************************BotoolUserTest*****************************/

				//@Test
				public void findUserByPrimaryKeyTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode findUserByPrimaryKeyTest");
					BotoolUser foundUser = userRepository.findOne((long) 1);
					LOGGER.info("Résultat : " + foundUser.toString());
					assertNotNull(foundUser);
					assertThat(foundUser.getLogin()).isEqualTo("salma2003");
					LOGGER.info("Fin d'exécution de test unitaire de méthode findUserByPrimaryKeyTest");

				}

				@Test
				public void GetAllUsersTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode GetAllUsersTest ");
					List<BotoolUser> result = userRepository.findAll();
					assertNotNull(result);
					result.forEach(i -> LOGGER.info("Résultats : " + i.toString()));
					LOGGER.info("Fin d'exécution de test unitaire de méthode GetAllUsersTest ");
				}

				//@Test
				public void saveUserTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode saveUserTest ");
					BotoolUser user = new BotoolUser();
					user.setLogin("login test");
					user.setMatricule("matricule test");
					userRepository.save(user);
					assertNotNull(user);
					LOGGER.info("Fin d'exécution de test unitaire de méthode saveUserTest ");

				}
				
				//@Test
				public void deleteUserTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode deleteUserTest ");
					userRepository.delete((long) 42);
					LOGGER.info("Fin d'exécution de test unitaire de méthode deleteUserTest ");

				}
				
				/*************************BotoolProfilTest*****************************/
				@Test
				public void findProfilByPrimaryKeyTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode findProfilByPrimaryKeyTest");
					Profils foundProfil = profilRepository.findOne((long) 1);
					LOGGER.info("Résultat : " + foundProfil.toString());
					assertNotNull(foundProfil);
					assertThat(foundProfil.getProfilName()).isEqualTo("ADMIN");
					LOGGER.info("Fin d'exécution de test unitaire de méthode findProfilByPrimaryKeyTest");

				}

				@Test
				public void GetAllProfilsTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode GetAllProfilsTest ");
					List<Profils> result = profilRepository.findAll();
					assertNotNull(result);
					result.forEach(i -> LOGGER.info("Résultats : " + i.toString()));
					LOGGER.info("Fin d'exécution de test unitaire de méthode GetAllProfilsTest ");
				}

				@Test
				public void saveProfilTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode saveProfilTest ");
					Profils profil = new Profils();
					profil.setProfilName("profil test");
					profilRepository.save(profil);
					assertNotNull(profil);
					LOGGER.info("Fin d'exécution de test unitaire de méthode saveProfilTest ");

				}
				
				//@Test
				public void deleteProfilTest() {
					LOGGER.info("Début d'exécution de test unitaire de méthode deleteProfilTest ");
					profilRepository.delete((long) 151);
					LOGGER.info("Fin d'exécution de test unitaire de méthode deleteProfilTest ");

				}

}
