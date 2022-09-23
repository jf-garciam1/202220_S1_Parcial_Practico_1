package co.edu.uniandes.dse.parcialejemplo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;


@Service
public class HabitacionService {

    
    @Autowired
    HabitacionRepository habitacionRepository;

    @Transactional
	public HabitacionEntity createHabitacion(HabitacionEntity habitacionEntity) throws EntityNotFoundException, IllegalOperationException {
		
		/*private Long id;
    private String personasxhab;
    private String numid;
    private String numcamas;
    private String numbaños;
    
    
    @PodamExclude
    @ManyToOne()
    private HotelEntity hotel; */
		if (habitacionEntity.getnumid() == null)
			throw new IllegalOperationException("numid is not valid");
        
        if (habitacionEntity.getnumcamas() == null)
			throw new IllegalOperationException("numcamas is not valid");

        if (habitacionEntity.getnumbaños() == null)
			throw new IllegalOperationException("numbaños is not valid");
        
        if (habitacionEntity.getpersonaschab() == null)
			throw new IllegalOperationException("personasxhab is not valid");
           
		
        if (habitacionEntity.getId()==null)
            throw new IllegalOperationException("Id is not valid");
        
        
        if (habitacionEntity.getnumid()>habitacionEntity.getnumcamas()
            throw new IllegalOperationException("Habi>numcamas is not valid");

       
        if (!habitacionRepository.findById(HabitacionEntity.getId()).isEmpty()){
             throw new IllegalOperationException("Ya existe Habitacion registrado con ese id");

		
		return HabitacionRepository.save(habitacionEntity);
	}

}