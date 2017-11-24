package com.hasanlo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_role")
public class UserRole implements Serializable {
	@Transient
	private static final long serialVersionUID = 123L;


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@ManyToOne
	private User user;

	@Column(name="role")
	private String role;

	private UserRole(){}


	public UserRole(String role, User user) {
		this.role = role;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getRole() {
		return role;
	}
}
