package tn.esprit.spring;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import tn.esprit.spring.dto.EmployeDTo;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeServiceImplTest {
	@Autowired
	IEmployeService iempserv;
	
	@Autowired
    EmployeRepository empr;
	@Autowired
	IEntrepriseService entrs;
	@Autowired
   ContratRepository contratrepo;
	
	@Autowired
	   DepartementRepository deprepo;
		
	Integer idE=1;
	private static final Logger l =LogManager.getLogger(EmployeServiceImplTest.class);
	

	@Test
	public void testaddOrUpdateEmploye()  {      
		 l.info("in testaddOrUpdateEmploye()");
		 idE=iempserv.addOrUpdateEmploye(new EmployeDTo("siwar","siwar", "k@yahoo.tn","xx", true, Role.ADMINISTRATEUR));
		 Assert.notNull(idE,"employe added failed");
		 l.info("Out testaddOrUpdateEmploye()");
	}
	

	
	@Test
	public void testauthenticate() {
		Employe e =iempserv.authenticate("k@yahoo.tn","xx");
		assertNotNull(e);	
	}

	@Test
	public void testgetAllEmployes(){
		 l.info("In testgetAllEmployes()");
		 List<Employe> employes =iempserv.getAllEmployes();
	     Assert.notNull(employes,"get employes null");
	     l.info("Out testgetAllEmployes()");
	}

	@Test
	public void testgetEmployePrenomById() {
		 l.info("In testgetEmployePrenomById()");
		 Assert.notNull(iempserv.getEmployePrenomById(idE),"prenom null");
		 l.info("Out testgetEmployePrenomById()");
	}
	
	@Test
	public void testgetSalaireByEmployeIdJPQL() {
		 l.info("In testgetSalaireByEmployeIdJPQL()");
		 assertNotNull(iempserv.getSalaireByEmployeIdJPQL(idE));	
		 l.info("Out testgetSalaireByEmployeIdJPQL()");
	}
	


	@Test
	public void testgetNombreEmployeJPQL() {
		l.info("In testgetNombreEmployeJPQL()");
		 Integer nombre = iempserv.getNombreEmployeJPQL();
		 assertNotNull(nombre);	
		 l.info("Out testgetNombreEmployeJPQL()");
				 
	}
	

		@Test
	public void testgetAllEmployeNamesJPQL(){
			l.info("In testgetAllEmployeNamesJPQL()");
		List<String> le =iempserv.getAllEmployeNamesJPQL();
		 assertNotNull(le);	
		 l.info("Out testgetAllEmployeNamesJPQL()");
	}
	

		@Test
	public void testaffecterContratAEmploye() {		
			l.info("In testaffecterContratAEmploye()");
		int contratId = 1; 
		Contrat cont = contratrepo.findById(contratId).orElse(null);
		Employe emp = empr.findById(idE).orElse(null);
		if(cont!=null)
			{
				cont.setEmploye(emp);
				iempserv.affecterContratAEmploye(contratId, idE);
				
			}
			 Assert.notNull(cont,"contrat null");
			 l.info("Out testaffecterContratAEmploye()");
		}
		
	
	@Test
	public void testgetAllEmployeByEntreprise() {
		l.info("In testgetAllEmployeByEntreprise()");
		Entreprise e =entrs.getEntrepriseById(2);
		List<Employe> employes = iempserv.getAllEmployeByEntreprise(e);
	    assertNotNull(employes);	
	    l.info("Out testgetAllEmployeByEntreprise()");
	}
	
	
	@Test
	public void testaffecterEmployeADepartement() {
		l.info("In testaffecterEmployeADepartement()");
		int departementId = 1 ;
		Optional<Employe> employe = empr.findById(idE);
		iempserv.affecterEmployeADepartement(idE, departementId);
		 assertNotNull(employe);
		 l.info("Out testaffecterEmployeADepartement()");
	}

		@Test
	public void testdesaffecterEmployeDuDepartement() {
	    l.info("In testdesaffecterEmployeDuDepartement()");
		int depId =1;
		iempserv.desaffecterEmployeDuDepartement(idE, depId);
		Optional<Employe> employe = empr.findById(idE);
	    assertNotNull(employe);
	    l.info("Out testdesaffecterEmployeDuDepartement()");
	}
	
		

		@Test
		public void testmettreAjourEmailByEmployeId() {
			   l.info("In testmettreAjourEmailByEmployeId()");
			iempserv.mettreAjourEmailByEmployeId("modifier@mail.com", idE);
			 l.info("Out testmettreAjourEmailByEmployeId()");
		}
	

		@Test
		public void testmettreAjourEmailByEmployeIdJPQL() {
			 l.info("In testmettreAjourEmailByEmployeIdJPQL()");
			iempserv.mettreAjourEmailByEmployeIdJPQL("modifierjpql@mail.com", idE);
			 l.info("Out testmettreAjourEmailByEmployeIdJPQL()");
		}

		
		@Test
		public void testdeleteEmployeById()
		{  Integer id=8;
				l.info("In methode testDeleteEmployeById()");
				int i = iempserv.deleteEmployeById(id);
				assertEquals(1, i);
				l.info("Out methode testDeleteEmployeById()");
		}

	
}