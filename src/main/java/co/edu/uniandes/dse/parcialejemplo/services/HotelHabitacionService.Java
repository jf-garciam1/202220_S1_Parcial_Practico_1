package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;

import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelHabitacionService {
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private HabitacionRepository habitacionRepository;
	
	
	


	@Transactional
	public HabitacionEntity addHotelHabitacion(Long hotelId, Long habitacionId) throws EntityNotFoundException, IllegalOperationException {
		
		Optional<HabitacionEntity> habitacionEntity = habitacionRepository.findById(habitacionId);
		if(habitacionEntity.isEmpty())
			throw new EntityNotFoundException("habitacion no encontrada");
		
		Optional<HotelEntity> hotelEntity = hotelRepository.findById(hotelId);
		if(hotelEntity.isEmpty())
			throw new EntityNotFoundException("Hotel no encontrado");

		if (habitacionEntity.get().getVendido() || habitacionEntity.get().getReservado()){
				throw new IllegalOperationException("Habitacion ya esta vendido o reservado");
			}
		
		else{
			hotelEntity.get().getHabitacions().add(habitacionEntity.get());
			// Actualizar el costo total de la compra
			hotelEntity.get().setTotal(hotelEntity.get().getTotal()+habitacionEntity.get().getPrecio());	
			log.info("Termina proceso de agregarle una habitacion al compra con id = {0}", hotelId);
			return habitacionEntity.get();
			}
		/* Codigo viejo por si las moscas
		habitacionEntity.get().setHotel(hotelEntity.get());
		log.info("Termina proceso de agregarle una habitacion al hotel con id = {0}", hotelId);
		return habitacionEntity.get();*/
	}