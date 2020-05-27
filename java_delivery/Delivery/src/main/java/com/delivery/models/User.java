package com.delivery.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Builder;

@Entity
@Table(name="user")
public class User implements UserDetails {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 8455416597601356832L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
 
    @Column(nullable = false, unique = true)
    private String username;
 
    @NotEmpty
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<> ();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Object orElseThrow(User user) {
		if (user == null)
			throw new UsernameNotFoundException("Username not found");
		else 
			return user;
	}
	
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void addRole(String newRole) {
		this.roles.add(newRole);
	}
	
	public void removeRole(String role) {
		for (String s : roles)
		{
			if (s.equals(role)) roles.remove(roles.indexOf(s));
		}
	}
}