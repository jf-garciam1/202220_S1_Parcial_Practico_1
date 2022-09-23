package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad genérica de la que heredan todas las entidades. Contiene la
 * referencia al atributo id
 * (15%) Cree la entidad _MedicoEntity_ 
 * en la carpeta correspondiente. Un médico tiene un nombre, un apellido, un registro médico, una especilidad y un id de tipo _Long_ que representa su llave primaria.
 *
 * @author ISIS2603
 */


@Entity
@Getter
@Setter
public class HotelEntity extends BaseEntity {
	private Long id;
    private String nombre;
    private String direccion;
    private String numestrellas;
     
    @PodamExclude
	@OneToMany(mappedBy="habitacion", fetch = FetchType.LAZY)
	private List<HabitacionEntity> habitaciones = new ArrayList<HabitacionEntity>();
    
}

