
package ar.com.codoacodo.service.impl;

import ar.com.codoacodo.domain.User;
import ar.com.codoacodo.repository.UserRepository;
import ar.com.codoacodo.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
 

//lombok es una libreria que hace modificaciones a nivel bycode
//lombok toma el .class y hace modificaciones en runtime

@RequiredArgsConstructor //esta anotación es de Lombok y reemplaza al constructor 
@Service 
public class UserServiceImpl implements UserService {
   
    //defino un atributo privado
    private final UserRepository repository;
    
    /*constructor reemplazado por anotacion de lombok línea 14
    //Constructor
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;    
    }
    */
    
    //métodos que se implementan de UserService___________________________________________
    @Override
    public void crearUser(User user) {
        //lógica para INSERT
        this.repository.save(user);//con esta línea estamos: INSERT INTO TABLE (C1,C2,... Cn) values(V1,V2,... Vn)
        
    }

    /**
     *
     * @param id
     * @return 
     */
    @Override
    public User buscarUser(Long id) {
        //lógica para SELECT
    	
    	return this.repository.findById(id).get(); //con esta línea estamos: SELECT * FROM TABLE WHERE ID=ID 	
    }

	@Override
	public List<User> buscarTodos() {
		return this.repository.findAll(); 
	}

	@Override
	public User buscarUserPorUsername(String username) {
		// creamos un método que reemplaza a: Select * from tabla where username ="userbuscado"
		return this.repository.findByUsername(username);
	}

	@Override
	public void eliminarUser(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public void actualizar(User user) {
		this.repository.save(user);
		
	}
    
}
