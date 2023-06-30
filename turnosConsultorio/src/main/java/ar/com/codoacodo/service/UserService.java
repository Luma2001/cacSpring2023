
package ar.com.codoacodo.service;

import java.util.List;

import ar.com.codoacodo.domain.User; 

/*
 *Éste es el servicio que usa la entidad.
 *Usamos una interface, definimos los métodos que va a usar el controlador, 
 *un caso de uso y depués recién llegamos a la base de datos
 */
    

public interface UserService {

    /*
     * Definimos atributos.
     * Recordar si tengo una interface los atributos no pueden ser privados
     * deben ser públicos
    */
    public void crearUser(User user); 
    public User buscarUser(Long id);
	public List<User> buscarTodos();
	public User buscarUserPorUsername(String username);
	public void eliminarUser(Long id);
	public void actualizar(User user);
	
    
}
