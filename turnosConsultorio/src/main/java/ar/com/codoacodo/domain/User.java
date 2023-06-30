package ar.com.codoacodo.domain;

import java.util.Set;



import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

/*Esta clase representa la tabla USER en la base de datos*/

/*Aplicando JPA*/


@Entity//entidad
@Table(name = "user")
@Data //para que agregue los getter y setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	//@JsonIgnore//anotación de presentación, para que no muestre un dato en el frontend
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
        @Column(name = "username", length = 50, unique = true)
	private String username;
        //@JsonIgnore
        @Column(name = "password", length = 50)
	private String password;
	
//hacemos la integración de la tabla user_roles
        
        @ManyToMany(fetch =FetchType.EAGER)
        @JoinTable(
        		name = "user_roles",
        		joinColumns = @JoinColumn(name="user_id"),
        		inverseJoinColumns = @JoinColumn(name="rol_id")
        		)
        private Set<Rol> roles;
        /*es lo mismo que escribir
         * select * from user u
         * 			inner join user_roles ur on ur.user_id = u.id
         * 			inner join role r on r.id = ur.rol_id
         * */

}
