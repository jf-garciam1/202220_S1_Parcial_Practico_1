package co.edu.uniandes.dse.parcialejemplo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;


@Service
public class HotelService {

    
    @Autowired
    HotelRepository hotelRepository;

    @Transactional
	public HotelEntity createHotel(HotelEntity hotelEntity) throws EntityNotFoundException, IllegalOperationException {
		
		
		if (hotelEntity.getNombre() == null)
			throw new IllegalOperationException("Nombre is not valid");
        
        if (hotelEntity.getnumestrellas() == null)
			throw new IllegalOperationException("Apellido is not valid");

        if (hotelEntity.getDireccion() == null)
			throw new IllegalOperationException("Registro is not valid");
           
		
        if (hotelEntity.getId()==null)
            throw new IllegalOperationException("Id is not valid");
        
        
        if (hotelEntity.getnumestrellas()>5
            throw new IllegalOperationException("NumEstrellas is not valid");

        if (hotelEntity.getnumestrellas()<1
            throw new IllegalOperationException("NumEstrellas is not valid");
       
        if (!hotelRepository.findById(HotelEntity.getId()).isEmpty()){
             throw new IllegalOperationException("Ya existe Hotel registrado con ese id");

        if (!hotelRepository.findByNombre(HotelEntity.getNombre()).isEmpty()){
            throw new IllegalOperationException("Ya existe Hotel registrado con ese nombre");
            }

		
		return HotelRepository.save(hotelEntity);
	}

}
