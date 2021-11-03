package tn.esprit.spring.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import tn.esprit.spring.dto.EntrepriseDTo;
import tn.esprit.spring.entities.Entreprise;

@Component
public class EntrepriseConverter {
	//Transformer entreprise DTO en entreprise
    public Entreprise entrepriseTodo(EntrepriseDTo entreprise) {
 	   ModelMapper mapper =new ModelMapper();
 	  Entreprise map = mapper.map(entreprise, Entreprise.class);
		return map;
    }
    
    //Transformer entreprise en entreprise DTO
    public EntrepriseDTo entityToDto(Entreprise entreprise) {
		ModelMapper mapper =new ModelMapper();
		EntrepriseDTo map = mapper.map(entreprise, EntrepriseDTo.class);
		return map;
		
	}
    //Retourner la liste des departement DTO
    public  List<EntrepriseDTo> entrepriselistToDto(List<Entreprise> Entreprise) {
		return	Entreprise.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
		
	}
}
