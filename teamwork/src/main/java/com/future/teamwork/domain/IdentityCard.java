package com.future.teamwork.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="identity_card")
public class IdentityCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3836346112659290983L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    	
    @Column(name="name")
    private String name;
    
    @Column(name="cardNum")
    private String cardNum;
    
    //IdentityCard

//    @OneToOne(mappedBy="identityCard",optional=false) 
//    private Person person;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
    
    
	
}
