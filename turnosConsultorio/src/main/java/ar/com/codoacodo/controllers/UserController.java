/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codoacodo.controllers;

import ar.com.codoacodo.Dto.UserDTO;
import ar.com.codoacodo.Dto.UserRequestDTO;
import ar.com.codoacodo.Dto.UserRequestPutDTO;
import ar.com.codoacodo.Dto.UserResponseDTO;
import ar.com.codoacodo.domain.Rol;
import ar.com.codoacodo.domain.User;
import ar.com.codoacodo.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:5500")

@RestController //es el responsable de exponer las repuestas a los queries para el que necesite hacer la consulta,  la http://locahost:8080/user la dirección html de conexion local
@RequestMapping("/user") //nombre tabla. El request mapping lo atiende(escucha) la clase controler
@RequiredArgsConstructor //anotacion de Lombok para no construir el constructor tradicional
public class UserController {
    //GET, POST, PUT , DELETE  verbos para los queries que hace el frontend
    
    //necesito el service, para ello creo una variable
    private final UserService userService;
    
    
    //para obtener un recurso uso el verbo GET
    @GetMapping("/{id}") //el user/1 que coloco en la dirección es un get
    public ResponseEntity <UserDTO> usuario(@PathVariable("id") Long id){//ResponseEntity es un objeto de spring que nos permite devolver algo
        User user = this.userService.buscarUser(id); 
        
        UserDTO dto = UserDTO.builder()
        					 .id(user.getId())
        					 .username(user.getUsername())
        					 .roles(user.getRoles()
        							 	.stream()
        							 	.map(x -> x.getRol())
        							 	.collect(Collectors.toSet())
        					  ).build();
        
        
        //devuelve un http status code = 200 que significa que la petición se completo sin errores, fue exitosa
        return ResponseEntity.ok(dto);
    }
    
    //Creamos una petición para obtener todos los user existentes en BBDD
    @GetMapping()
    public ResponseEntity<List<User>> findAll(){
    	List<User> user = this.userService.buscarTodos();
    	return ResponseEntity.ok(user);
    }
    
    
    //para crear un recurso uso el verbo POST
    @PostMapping()
    @PreAuthorize(value = "hasAuthority('ROL_ADMIN')")
   public ResponseEntity<UserResponseDTO> crearUsuario(@RequestBody UserRequestDTO request){
    	
    	//verifico si existe username
    	User user = this.userService.buscarUserPorUsername(request.getUsername());
    	if(user!=null) {
    		UserResponseDTO response = UserResponseDTO.builder()
    												  .username(user.getUsername())
    												  .build();
    		return ResponseEntity.ok(response);
    	}else {
    	
    	//si no existe lo crea
    	
    	 //validacion. Cada punto en el builder es como un SET de User
		
    	Set<Rol> rolesDelUsuario = request.getRoles()
			.stream()
			.map(r -> Rol.builder().id(Long.parseLong(r)).build())
			.collect(Collectors.toSet());	
			
    		
    		
    	User newUser = User.builder()
			    		   .username(request.getUsername())
			    		   .password(new BCryptPasswordEncoder().encode(request.getPassword()))
			    		   .roles(rolesDelUsuario)
			    		   .build();
    	 
    	this.userService.crearUser(newUser);
    	
    	UserResponseDTO response = UserResponseDTO.builder()
					    						  .username(newUser.getUsername())
					    						  .build();
    	
    	return ResponseEntity.ok(response);
    	}
    }
    
    //para borrar un recurso uso el verbo DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserbyId(
    		@PathVariable("id") Long id
    		){
    	this.userService.eliminarUser(id);
    	return ResponseEntity.ok().build();
    }
    
    
    //para actualizar los valores o valor de un recurso uso el verbo PUT
    @PutMapping("/{id}")
    public ResponseEntity<UserRequestPutDTO> actualizarXPut(
    		@PathVariable(name="id", required=true) Long id,
    		@Validated @RequestBody UserRequestPutDTO request
    		){
    	User user = this.userService.buscarUser(id);
    	if(!user.getId().equals(request.getId())) {
    		return ResponseEntity.badRequest().build();
    	}
    	user.setPassword(request.getPassword());
    	//acá puedo poner los atributos que quiero actualizar
    	this.userService.actualizar(user);
    	return ResponseEntity.ok().build();
    }
    
    //path
    
}//fin class
 