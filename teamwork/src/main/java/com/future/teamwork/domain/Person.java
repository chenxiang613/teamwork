package com.future.teamwork.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="person")
@Data
public class Person extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6590337224942700688L;

    	
    @Column(name="phone")
    private String phone;
    
    @OneToMany(mappedBy="person")
    private Set<Order> orderSet;
	

}
