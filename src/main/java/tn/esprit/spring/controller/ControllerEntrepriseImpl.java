package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.dto.EntrepriseDTo;

@Controller
public class ControllerEntrepriseImpl{

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;

	public int ajouterEntreprise(EntrepriseDTo ssiiConsulting) {
		ientrepriseservice.ajouterEntreprise(ssiiConsulting);
		return ssiiConsulting.getId();
	}
	
	public void deleteEntrepriseById(int entrepriseId)
	{
		ientrepriseservice.deleteEntrepriseById(entrepriseId);
	}
	public Entreprise getEntrepriseById(int entrepriseId) {

		return ientrepriseservice.getEntrepriseById(entrepriseId);
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
	}
}
