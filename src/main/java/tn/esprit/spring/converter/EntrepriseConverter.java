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
 	 return mapper.map(entreprise, Entreprise.class);

    }
    
    //Transformer entreprise en entreprise DTO
    public EntrepriseDTo entityToDto(Entreprise entreprise) {
		ModelMapper mapper =new ModelMapper();
		return mapper.map(entreprise, EntrepriseDTo.class);
		
	}
    //Retourner la liste des departement DTO
    public  List<EntrepriseDTo> entrepriselistToDto(List<Entreprise> entreprise) {
		return	entreprise.stream().map(this::entityToDto).collect(Collectors.toList());
		
	}
}
