package com.devsuperior.dscatalog.entities;

import java.util.Objects;
import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;


public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firsttName;
	private String lastName;
	private String email;
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	public User() {}

	public Long getId() {
		return id;
	}

	public User(Long id, String firsttName, String lastName, String email, String password) {
		this.id = id;
		this.firsttName = firsttName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirsttName() {
		return firsttName;
	}

	public void setFirsttName(String firsttName) {
		this.firsttName = firsttName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}
