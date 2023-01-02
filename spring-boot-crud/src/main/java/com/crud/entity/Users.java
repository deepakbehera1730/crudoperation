package com.crud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "users")
//***soft deleted***
//@Where(clause = "is_active")
//@SQLDelete(sql = "UPDATE users set is_active=false  where id=?")

public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@NotEmpty
	private String name;
	@Email(message = "EMAIL IS NOT VALID")
	private String email;
	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updateAt;

	private Boolean isActive = true;
	
	
	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Users(long id, String name,String email, Date createdAt,
			Date updateAt, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.isActive = isActive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	 

	public Users() {
		super();
	}



	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
