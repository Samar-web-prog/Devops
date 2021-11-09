package tn.esprit.spring.converter;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tn.esprit.spring.dto.DepartementDTo;
import tn.esprit.spring.entities.Departement;
import java.util.stream.Collectors;

@Component
public class DepartementConverter {
	
	   //Transformer departement DTO en Departement
       public Departement depTodo(DepartementDTo departement) {
    	   ModelMapper mapper =new ModelMapper();
    	   return mapper.map(departement, Departement.class);
       }
       
       //Transformer departement en deprtement DTO
       public DepartementDTo entityToDto(Departement dep) {
   		ModelMapper mapper =new ModelMapper();
   		return mapper.map(dep, DepartementDTo.class);
   		
   	}
       //Retourner la liste des departement DTO
       public  List<DepartementDTo> deplistToDto(List<Departement> departement) {
   		return	departement.stream().map(this::entityToDto).collect(Collectors.toList());
   		
   	}
	
	
}
