package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	@Override
	public Employe authenticate(String login, String password) {
			
		try {
			l.info("In authenticate()");
			l.debug("we will do the authentification");
			Employe authenticate=employeRepository.getEmployeByEmailAndPassword(login, password);
			l.debug("authenticate done");
			l.info("Out authenticate()");
			return authenticate;
			
		}
		catch (Exception e) {
		       l.error("erreur in  authenticate() :" +e);	
		       return null;       
				}
	}
	
	

	@Override
	public Integer addOrUpdateEmploye(Employe employe) {
		
		try {
			l.info("In addOrUpdateEmploye()");
			l.debug("je vais enregistrer ou modifier l'employe");
			employeRepository.save(employe);
			l.debug("je viens de finir la modification ou l'ajout de l'employe portant l'id: "+employe.getId() );
			l.info("Out addOrUpdateEmploye()");
		return employe.getId();
		}
		catch (Exception e) {
		       l.error("erreur in methode addOrUpdateEmploye() :" +e);	
		       return null;       
				}	
	}


	
	
	
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		
		try {
			l.info("In mettreAjourEmailByEmployeId()");
			
			
			l.debug("je vais recuperer l'id de l'employee");
		Employe employe =employeRepository.findById(employeId).orElse(null);	
		
		
		if(employe!=null){
			
			l.debug("je vais modifier l'email "+ email +"de l'employe portant l'id"+employe.getId());
		employe.setEmail(email);
		employeRepository.save(employe);	
		l.debug("je viens de modifier l'email de l'employe "+employe.getId());
		l.info("Out mettreAjourEmailByEmployeId avec success ");
		
		}
		}
		catch (Exception e) {
			l.error("erreur in methode mettreAjourEmailByEmployeId(): " +e);
		}

	}

	
	
	
	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		
try {
	l.info("In affecterEmployeADepartement()");
	
	l.debug("je vais recuperer le departement");
	Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
	
	l.debug("je vais recuperer l'employe");
	Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
	 if(depManagedEntity!=null)
		if( depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			l.debug("je vais remplir la liste des employes");
			employes.add(employeManagedEntity);
			l.debug("je vais modifier la liste des employes dans le departement");
			depManagedEntity.setEmployes(employes);
		}else{
			l.debug("je vais affecter les employes au departement");
			depManagedEntity.getEmployes().add(employeManagedEntity);
		}


		deptRepoistory.save(depManagedEntity); 
		l.debug("je viens de finir effecterEmployeAdepartement ");
		l.info("Out effecterEmployeAdepartement()");
		}
catch (Exception e) {
	l.error("erreur in methode affecterEmployeADepartement() : " +e);
	
}		

	}
	
	
	
	
	
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		
		try{
			l.info("In desaffecterEmployeDuDepartement() ");
			
			
			l.debug("je vais recuperer le departement selon l'id ");
		Departement dep = deptRepoistory.findById(depId).orElse(null);
if(dep!=null){
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				l.debug("je vais supprimer un employe dans un departement");
				dep.getEmployes().remove(index);
				break;
				
			}
			l.debug("je viens de finir desaffecterEmployeDuDepartement");
			l.info("Out desaffecterEmployeDuDepartement()");
		}}
		}
		catch (Exception e) {
			l.error("erreur in methode desaffecterEmployeDuDepartement() : " +e);}
	} 
	
	// Tablesapce (espace disque) 



	public void affecterContratAEmploye(int contratId, int employeId) {
	
		try {	
			l.info("In affecterContratAEmploye() ");
			l.debug("je vais recuperer le contrat by id");
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		l.debug("recuperation de contrat"+contratManagedEntity);
		
		
		l.debug("je vais recuperer l'employe by id");
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
	
		l.debug("je vais affecter le contrat à un employe");
		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		l.debug(" je viens de finir  affecterContratAEmploye ");
		l.info(" out affecterContratAEmploye() ");
		}
		
		catch (Exception e) {
			l.error("erreur in methode affecterContratAEmploye() : " +e);
			
		}
		

	}

	
	
	public String getEmployePrenomById(int employeId) {
	
		try {
			l.info(" In getEmployePrenomById() ");
			
			l.debug("je vais recuperer l'employe by id ");
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		
		l.debug("recuperation getEmployeById "+employeManagedEntity!=null ?employeManagedEntity.getPrenom():null);
		l.info(" out getEmployePrenomById() ");
		return employeManagedEntity!=null ?employeManagedEntity.getPrenom():null;
	
		}
		catch (Exception e) {
			l.error("erreur in methode getEmployeeById() : " +e);
			return null;
		}
		

	}

	
	
	
	public int deleteEmployeById(int employeId)
	{
		
		
		try {
			l.info("In deleteEmployeById ");
			
			l.debug("je vais recuperer l'employe selon l'id ");
		Employe employe = employeRepository.findById(employeId).orElse(null);

		
	if(employe!=null && employe.getDepartements()!=null)
		for(Departement dep : employe.getDepartements()){
			l.debug("je vais desaffecter l'employe d'un departement ");
			dep.getEmployes().remove(employe);
		
		}
	l.debug("je vais supprimer l'employe par son id"+employeId);
		employeRepository.delete(employe);
		l.debug("deleteEmployeById ");
		l.info("Out deleteEmployeById with success");
		return -1;
		}
		catch (Exception e) {
			l.error("erreur methode deleteEmpolyeById() : " +e);
			return 0;
		}
	}



	
	
	public int getNombreEmployeJPQL() {
		
		try {
			l.info("In deleteEmployeById ");
			l.debug("je vais recevoir le nombre des employes ");
		
			int nbremploye=employeRepository.countemp();
			
			l.debug("le nombre des employes est "+nbremploye);
			l.info("Out deleteEmployeById");
			return nbremploye;
		
		}

		catch (Exception e) {
			l.error("erreur methode getNombreEmployeJPQL : " +e);
			return 0;
		}
		
		
		
		
	}

	public List<String> getAllEmployeNamesJPQL() {
		
		try
		{
			l.info("In getAllEmployeNamesJPQL ");			
			l.debug("je vais recuperer les employes By names");
			
			List<String> employeesByNames=employeRepository.employeNames();
			l.debug("la liste des employes by names"+employeesByNames);
			l.info("Out getAllEmployeNamesJPQL with success");
			return employeesByNames;
		}
		catch (Exception e) {
			l.error("erreur in methode getAllEmployeNamesJPQL : " +e);
			return null;
		}
		
		
		

	}

	
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		
		try
		{
			l.info("In getAllEmployeByEntreprise ");			
			l.debug("je vais recuperer des employes By entreprise");
			List<Employe> allemployees=employeRepository.getAllEmployeByEntreprisec(entreprise);
			l.debug("la liste des employeesByentreprise"+allemployees);
			l.info("Out getAllEmployeByEntreprise with success");
			return allemployees;
		}
		catch (Exception e) {
			l.error("erreur in methode getAllEmployeByEntreprise : " +e);
			return null;
		}
		
	}

	
	
	
	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		
		try
		{
			l.info("In mettreAjourEmailByEmployeIdJPQL ");			
			l.debug("je vais mettre a jour email by employe");
			employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
			l.debug("l'email est mis à ajour");
			l.info("Out mettreAjourEmailByEmployeIdJPQL with success");
			
			
		}
		catch (Exception e) {
			l.error("erreur in methode mettreAjourEmailByEmployeIdJPQL : " +e);
			
		}
	
		

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		
		try
		{
			l.info("methode getSalaireByEmployeIdJPQL ");		
			l.debug("je vais recuperer le salaire de l'employe by id");
			
			float salairebyembyid=employeRepository.getSalaireByEmployeIdJPQL(employeId);
			l.debug("le salaire de l'employe by id est"+salairebyembyid);
			l.info("Out getSalaireByEmployeIdJPQL with success");
			
			return salairebyembyid;
		}
		catch (Exception e) {
			l.error("erreur in methode getSalaireByEmployeIdJPQL : " +e);
			return 0;
		}
	
		
		
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		
		try
		{
			l.info("methode getSalaireMoyenByDepartementId ");
			l.debug("je vais recuperer le salaire moyenBydepartement");
			double salaire=employeRepository.getSalaireMoyenByDepartementId(departementId);
			l.debug("le salaire de l'employe by departement est"+salaire);
			l.info("Out getSalaireMoyenByDepartementId with success");
			
			return salaire;
		}
		catch (Exception e) {
			l.error("erreur in methode getSalaireMoyenByDepartementId : " +e);
			return null;
		}
		
		
	}



	public List<Employe> getAllEmployes() {
		
		try
		{
			l.info("methode getAllEmployes ");
			l.debug("je vais recuperer la liste de tous les employees");
			List<Employe> listemployes=(List<Employe>) employeRepository.findAll();
			l.debug("la liste des employees est"+listemployes);
			l.info("Out getAllEmployes with success");
			
			return listemployes;
		}
		catch (Exception e) {
			l.error("erreur In getAllEmployes : " +e);
			return null;
		}
		
		
	}





}