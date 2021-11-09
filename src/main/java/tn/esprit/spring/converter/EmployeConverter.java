package tn.esprit.spring.converter;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tn.esprit.spring.dto.EmployeDTo;
import tn.esprit.spring.entities.Employe;
import java.util.stream.Collectors;

@Component
public class EmployeConverter {
	
	   //Transformer employe DTO en Employe
       public Employe empTodo(EmployeDTo employe) {
    	   ModelMapper mapper =new ModelMapper();
    	   return mapper.map(employe, Employe.class);
       }
       
       //Transformer employe en employe DTO
       public EmployeDTo entityToDto(Employe emp) {
   		ModelMapper mapper =new ModelMapper();
   		return mapper.map(emp, EmployeDTo.class);

   		
   	}
       //Retourner la liste des employe DTO
       public  List<EmployeDTo> emplistToDto(List<Employe> emp) {
   		return	emp.stream().map(this::entityToDto).collect(Collectors.toList());
   		
   	}
	
	
}
