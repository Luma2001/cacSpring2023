package ar.com.codoacodo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ar.com.codoacodo.domain.User;

public class UserSecurity implements UserDetails{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L; no sé de dónde salió
	//creo atributo
	private final User user;
	//Creo constructor 
	public UserSecurity(User userFromDB) {
		this.user=userFromDB;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.user.getRoles()
				.stream()
				.map(u -> u.getRol())//deberia devolver el listado de roles
				.map(SimpleGrantedAuthority::new)//método reference
				.toList();
	}

	@Override
	public String getPassword() {
		
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	

}
