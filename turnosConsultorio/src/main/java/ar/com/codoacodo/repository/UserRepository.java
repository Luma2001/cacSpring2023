
package ar.com.codoacodo.repository;
import ar.com.codoacodo.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository//repositorio que modifica la entidad.
public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);
	//Es una interfaz que hereda de otra interfaz
    //crud: 
    
    
}
