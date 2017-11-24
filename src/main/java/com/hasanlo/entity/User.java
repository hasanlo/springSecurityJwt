package com.hasanlo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "username")
    private String userName;   

	@Column(name = "password")
    private String password;   

	@Column(name = "email")
    private String email;
    
	@Column(name ="enabled")
	private boolean enabled;

	@OneToMany
	@JoinColumn(name="users_ID", referencedColumnName="ID")
	private Set<UserRole> userRoles;
	
	public User(){
		
	}

	public User(User user){
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
	}

	public User(String userName, String password, String email, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}