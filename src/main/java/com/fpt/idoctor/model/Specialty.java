package com.fpt.idoctor.model;
// Generated Mar 8, 2018 8:26:25 PM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fpt.idoctor.bean.SpecialtyBean;

/**
 * Specialty generated by hbm2java
 */
@Entity
@Table(name = "specialty")
public class Specialty implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>(0);
	private Set<Disease> diseases = new HashSet<Disease>(0);

	public Specialty() {
	}

	public Specialty(Long id) {
		this.id = id;
	}

	public Specialty(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Specialty(Long id, String name, String description, Set<User> users,
			Set<Disease> diseases) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = users;
		this.diseases = diseases;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy = "specialty")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "specialty")
	public Set<Disease> getDiseases() {
		return this.diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

	public SpecialtyBean convertToBean() {
		SpecialtyBean bean = new SpecialtyBean();
		bean.setId(id);
		bean.setDescription(description);
		bean.setName(name);
		return bean;
	}
}