package ar.com.codoacodo.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;
		
	private final JpaUserDetailsService jpaUserDetailsService;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				//.cors(AbstractHttpConfigurer::disable) //desactivamos estas configuraciones de seguridad
				.cors(cors -> corsConfigurationSource()) //para conectar con el frontend
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/v1/auth/**").permitAll()//todas estas peticiones estan permitidos
						.anyRequest().authenticated())//cualquier otra peticion debe ser autenticada
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//no quarda estado. lo guarda en el servidor
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)//nuestro filtro lo metimos adelante de lso otros filtoros
				.userDetailsService(jpaUserDetailsService)
				.build();
				
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {//configurando la habilitacion del cors
		  final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500"));//le decimos el origen, desde donde puedeo hacer peticiones
	        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));//métodos que puedo utilizar
	        configuration.setAllowCredentials(true);//si acepta las credenciales
	        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));//cabeceras que le puede enviar el servidor al frontend
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);//registramos objeto de configuracion
	        return source;
	  }
}
