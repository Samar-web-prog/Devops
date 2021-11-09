package tn.esprit.spring.converter;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tn.esprit.spring.dto.ContratDTo;
import tn.esprit.spring.entities.Contrat;

import java.util.stream.Collectors;

@Component
public class ContratConverter {
	
	   //Transformer Contrat Dto en contrat
       public Contrat contdto(ContratDTo nvcontrat) {
    	   ModelMapper mapper =new ModelMapper();
    	 return mapper.map(nvcontrat, Contrat.class);
   		
       }
       
       //Transformer contrat en contrat DTO
       public ContratDTo entityToDto(Contrat cont) {
   		ModelMapper mapper =new ModelMapper();
   		return mapper.map(cont, ContratDTo.class);
   		
   	}
       //Retourner la liste des Contrat DTO
       public  List<ContratDTo> contlistToDto(List<Contrat> contrat) {
   		return	contrat.stream().map(this::entityToDto).collect(Collectors.toList());
   		
   	}
	
	
}
