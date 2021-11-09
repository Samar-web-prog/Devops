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
    	  // Contrat c =new Contrat();
    	   //c.setName(contrat.getName());
    	   ModelMapper mapper =new ModelMapper();
    	   Contrat map = mapper.map(nvcontrat, Contrat.class);
   		return map;
       }
       
       //Transformer contrat en contrat DTO
       public ContratDTo entityToDto(Contrat cont) {
   		ModelMapper mapper =new ModelMapper();
   		ContratDTo map = mapper.map(cont, ContratDTo.class);
   		return map;
   		
   	}
       //Retourner la liste des Contrat DTO
       public  List<ContratDTo> contlistToDto(List<Contrat> Contrat) {
   		return	Contrat.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
   		
   	}
	
	
}
