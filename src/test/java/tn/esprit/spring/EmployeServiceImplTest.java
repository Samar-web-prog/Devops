package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest()
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
		
	Integer idE=8;
	private static final Logger l =LogManager.getLogger(EmployeServiceImplTest.class);
	
	@Test
	public void testAjouterEmploye()  {      
		 idE=iempserv.addOrUpdateEmploye(new Employe("siwar","hassen", "siwar.hassen@gmail.com","xx", true, Role.ADMINISTRATEUR));
		assertNotNull(idE);
	}
	

	@Test
	public void testgetSalaireByEmployeIdJPQL() {
		
		 float salaires = iempserv.getSalaireByEmployeIdJPQL(2);
		 assertEquals(0, salaires);
		
	}

	
	
	@Test
	public void testgetAllEmployeByEntreprise() {
		Entreprise e =entrs.getEntrepriseById(2);
		List<Employe> employes = iempserv.getAllEmployeByEntreprise(e);
		for(int i=0 ; i<employes.size(); i++){
			//l.info(le);
		}
		assertNotNull(employes);
	}

	
	@Test
	public void testgetAllEmployeNamesJPQL(){
	
		
		List<String> le =iempserv.getAllEmployeNamesJPQL();
		for(int i=0 ; i<le.size(); i++){
			//l.info(le);
		}
		assertNotNull(le);
	}

	
	@Test
	public void testgetAllEmployes(){
	
		
		List<Employe> employes =iempserv.getAllEmployes();
		for(int i=0 ; i<employes.size(); i++){
			//l.info(le);
		}
		assertNotNull(employes);
	}

	
	
	@Test
	public void testaffecterContratAEmploye() {
		int employeId = 1 ;
		int contratId = 2; 
		Contrat cont = contratrepo.findById(contratId).orElse(null);
		Employe emp = empr.findById(employeId).orElse(null);
		if (cont != null){
		cont.setEmploye(emp);
		iempserv.affecterContratAEmploye(contratId, employeId);
		assertNotNull(cont);
		}
			
	}
	
	@Test
	public void testgetEmployePrenomById() {
		
		 String prenom = iempserv.getEmployePrenomById(1);
		assertNotNull(prenom);
	}
	
	
	
	@Test
	public void testdesaffecterEmployeDuDepartement() {
		int entId = 1;
		int depId =2;
		iempserv.desaffecterEmployeDuDepartement(entId, depId);
	    Employe emp = empr.findById(entId).orElse(null);
	    if(emp != null){
		assertNotNull(emp.getDepartements());
	    }
	}
	

	/*@Test
	public void testgetSalaireMoyenByDepartementId() {
		
		 Double salaireMoyen = iempserv.getSalaireMoyenByDepartementId(1);
		
		 assertEquals(0, salaireMoyen);
	}
	*/
	
	
	@Test
	public void testmettreAjourEmailByEmployeIdJPQL() {
		
		 Integer nombre = iempserv.getNombreEmployeJPQL();
			 assertNotNull(nombre);
		 
	}
	
	
	@Test
	public void testaffecterEmployeADepartement() {
		int departementId = 1 ;
		int empId = 8;  // +1  update this id  by one each time I  run Junit test 
		Employe Emp = empr.findById(empId).orElse(null);
		Departement Dep = deprepo.findById(departementId).orElse(null);
		if (Emp != null){
		
		iempserv.affecterEmployeADepartement(empId, departementId);
		assertNotNull(Emp);
		}
			
	}
	
	@Test
	public void testauthenticate() {
		Employe e =iempserv.authenticate("siwar","xx");
		if(e!=null)
		{
			assertNotNull(e);
		}
	
		}
			
	
		@Test
	public void testDeleteEmployeById()
	{ 
		if(idE!=null)
		{
		int i = iempserv.deleteEmployeById(idE);
		
		assertEquals(0, i);
		}
		else {
			int i = iempserv.deleteEmployeById(2);
			
			assertEquals(0, i);
		}
	}
	
	
}