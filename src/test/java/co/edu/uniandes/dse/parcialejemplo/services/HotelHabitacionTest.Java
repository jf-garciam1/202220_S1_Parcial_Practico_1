package co.edu.uniandes.dse.parcialejemplo.services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelHabitacionService.class)
class HotelHabitacionServiceTest {
    @Autowired
    private HotelHabitacionService hotelhabitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HotelEntity> hotelList = new ArrayList<>();
    private List<HabitacionEntity> habitacionList = new ArrayList<>();


            /**
         * Configuración inicial de la prueba.
         */
    @BeforeEach
    void setUp() {
            clearData();
            insertData();
    }

    /**
         * Limpia las tablas que están implicadas en la prueba.
         */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ParteEntity");
        entityManager.getEntityManager().createQuery("delete from CategoriaEntity");
        entityManager.getEntityManager().createQuery("delete from CaracteristicaEntity");
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
            
        
        for (int i = 0; i < 3; i++) {
                HotelEntity hotelEntity = factory.manufacturePojo( HotelEntity.class);
                entityManager.persist(hotelEntity);
                hotelList.add(hotelEntity);
            }
        for (int i = 0; i < 3; i++) {
                HabitacionEntity habitacionEntity = factory.manufacturePojo( HabitacionEntity.class);
                entityManager.persist(habitacionEntity);
                habitacionList.add(habitacionEntity);
            }

            

          
    }
            

          
    }

    /**
	 * Prueba para crear un hotel
	 */
	@Test
	void testAddHotelHabitacion() throws EntityNotFoundException, IllegalOperationException {
		HotelEntity newHotel = factory.manufacturePojo (HotelEntity.class);
		entityManager.persist(newHotel);
		
		HabitacionEntity habitacion = factory.manufacturePojo(HabitacionEntity.class);
		entityManager.persist(habitacion);
		
        hotelhabitacionService.addHotelHabitacion(newHotel.getId(),habitacion.getId());
        
		List < HabitacionEntity >  persistenceHabitacions = hotelhabitacionService.getHabitacions(newHotel.getId());
		assertEquals(newHotel.getHabitacion().get(0), persistenceHabitacion.get(0));
        assertEquals(newHotel.getHabitacion().get(1), persistenceHabitacions.get(1));
        
	}

