package com.future.teamwork.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="role")
@Data
public class Role extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7812481407548123624L;


    @Column(name="role_name")
    private String roleName;
    
    @Column(name="permissions")
    private String permissions;

    @Column(name="role_desc")
    private String roleDesc;
    
    @Column(name="status")
    private Integer status;
    
    @ManyToMany(mappedBy="roleSet")
    @JsonIgnore private Set<User> userSet;

	
}