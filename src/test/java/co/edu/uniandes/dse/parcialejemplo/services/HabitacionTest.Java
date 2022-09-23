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

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HabitacionService.class)
class HabitacionServiceTest {
    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

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
                HabitacionEntity habitacionEntity = factory.manufacturePojo( HabitacionEntity.class);
                entityManager.persist(habitacionEntity);
                habitacionList.add(habitacionEntity);
            }

            

          
    }

    /**
	 * Prueba para crear un habitacion
	 */
	@Test
	void testCreateHabitacion() throws EntityNotFoundException, IllegalOperationException {
		HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
		HabitacionEntity result = habitacionService.createHabitacion(newEntity);
		assertNotNull(result);
		HabitacionEntity entity = entityManager.find(HabitacionEntity.class, result.getId());
		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getnumid(), entity.getnumid());
		assertEquals(newEntity.getnumcamas(), entity.getnumcamas());
		assertEquals(newEntity.getnumbaños(), entity.getnumbaños());
        assertEquals(newEntity.getpersonaschab(), entity.getpersonaschab());
        /*private Long id;
    private String personasxhab;
    private String numid;
    private String numcamas;
    private String numbaños;
    
    
    @PodamExclude
    @ManyToOne()
    private HotelEntity hotel; */
        

      
	}
}