package com.future.teamwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="sys_order")
@Data
public class Order extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6191093521595984453L;
    	
    @Column(name="address")
    private String address;
    
    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

}
