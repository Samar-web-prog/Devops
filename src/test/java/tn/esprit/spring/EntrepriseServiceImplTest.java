package tn.esprit.spring;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {
	private static final Logger l =LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Autowired
	IEntrepriseService ientrepriseservice;
	
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	Integer idE=1;
	
	// Tester la méthode qui permet d'ajouter une entreprise
	
	@Test
	public void testAjouterEntreprise()  {  
		l.info("In methode testAjouterEntreprise()");
		 idE=ientrepriseservice.ajouterEntreprise(new Entreprise("sabrine","sab "));
		assertNotNull(idE);
		l.info("Out methode testAjouterEntreprise()");
	}
	
	// Tester la méthode qui va envoyer l'entreprise dont son id est passé en parametre
	
	@Test
	public void testGetEntrpriseById() {
		l.info("In methode testGetEntrpriseById()");
		Entreprise e =ientrepriseservice.getEntrepriseById(idE); 
		assertNotNull(e);
		l.info("Out methode testGetEntrpriseById()");
	}
	
	// Tester la méthode qui va envoyer toute la liste des noms des départements selon l'id de l'entreprise
	// passé en paramétre
	
	@Test
	public void testGetAllDepartementsNamesByEntreprise() {
		l.info("In methode testGetAllDepartementsNamesByEntreprise()");
		List<String> depNames = ientrepriseservice.getAllDepartementsNamesByEntreprise(idE);
		assertNotNull(depNames);
		l.info("Out methode testGetAllDepartementsNamesByEntreprise()");
	}
	
	// Tester la méthode de supprimer de l'entreprise by id
	
	@Test
	public void testDeleteEntrepriseById()
	{
		l.info("In methode testDeleteEntrepriseById()");
		if(idE!=null){
		int i = ientrepriseservice.deleteEntrepriseById(idE);
		assertEquals(1, i);
		}
		l.info("Out methode testDeleteEntrepriseById()");
	}
	
}
