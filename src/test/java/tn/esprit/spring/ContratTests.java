package tn.esprit.spring;



import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ContratTests {
	private static final Logger l =LogManager.getLogger(ContratTests.class);
	@Autowired
	ContratServiceImpl cs;

	@Autowired
	ContratRepository cr;
	
	@Test
	public void testGetAllContrats(){
	
		
		List<Contrat> lc =cs.getAllContrats();
		for(int i=0 ; i<lc.size(); i++){
			l.info(lc);
			
		}
		Assert.notNull(lc,"contrat was null");
	}
	
	@Test
	public void testAjouterContrat(){
		Contrat nvcontrat = new Contrat(new Date() , "CDI" , 1250 );
		Integer idC=cs.ajouterContrat(nvcontrat);
		Assert.notNull(idC,"contrat n'est pas ajouté");
		
	   l.info("contrat ajouté avec succes");
	}
	
	@Test
	public void testDeleteContratById() {
		int referencecontrat = 3;
			int i=cs.deleteContratById(referencecontrat);
			l.info("contrat supprimé avec succes");
			Assert.notNull(i,"contrat n'est pas supprimé");
		
	}
	

	
}
