package ar.com.codoacodo.domain;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

/*Esta clase representa la tabla ROL en la base de datos*/

/*Aplicando JPA*/


@Entity//entidad
@Table(name = "rol")
@Data //para que agregue los getter y setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol {
	//@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    @Column(name = "rol", length = 50, unique = true)
	private String rol;
   
	public Rol(Long id) {
		this.id=id;
	}
	

}
