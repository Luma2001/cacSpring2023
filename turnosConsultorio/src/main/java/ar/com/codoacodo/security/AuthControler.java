package ar.com.codoacodo.security;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControler {


	private final AuthenticationManager authenticationManager;

	private final JpaUserDetailsService jpaUserDetailsService;

    private final JwtUtils jwtUtils;
    
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request,HttpServletResponse response) {


		try {// authenticationmanager
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                    				request.getUsername(), 
                    				request.getPassword(),
                    				new ArrayList<>())
                    			);
            // userdetails
            final UserDetails user = jpaUserDetailsService.loadUserByUsername(
            						request.getUsername());
            
            	if (user != null) {
            		// jwtutils
            		String jwt = jwtUtils.generateToken(user);
            		// response jwt
            		return ResponseEntity.ok(jwt);
            		}
            	
            return ResponseEntity.status(400).body("Error authenticating");
            
        } catch (Exception e) {
        	System.out.println(e);
        	return ResponseEntity.status(400).body("" + e.getMessage());
        }
		
	}//fin authenticate
}//Fin class


