package tn.esprit.spring.controller;

import org.primefaces.push.annotation.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;




@RestController
public class RestControlContrat {

	@Autowired
	IContratService icontratservice;
	
	// http://localhost:8081/SpringMVC/servlet/ajouterContrat
	@PostMapping("/ajouterContrat")
	@ResponseBody
	public int ajouterContrat(@RequestBody Contrat contrat) {
		
		icontratservice.ajouterContrat(contrat);
		return contrat.getReference();
	}
	
	 // URL : http://localhost:8081/SpringMVC/servlet/deleteContratById/2
    @DeleteMapping("/deleteContratById/{idcontrat}") 
	@ResponseBody
	public void deleteContratById(@PathVariable("idcontrat")int contratId) {
    	icontratservice.deleteContratById(contratId);
	}
    
    // URL : http://localhost:8081/SpringMVC/servlet/deleteAllContratJPQL
    @DeleteMapping("/deleteAllContratJPQL") 
	@ResponseBody
	public void deleteAllContratJPQL() {
    	icontratservice.deleteAllContratJPQL();
		
	}
    
    

}
