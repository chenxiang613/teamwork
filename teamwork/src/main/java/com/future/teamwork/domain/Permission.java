package com.future.teamwork.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Permission extends BaseEntity implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -7292890264937035695L;

    @Column(name="pid")
    private String pid;
    
    @Column(name="name")
    private String name;

    @Column(name="descpt")
    private String descpt;

    @Column(name="url")
    private String url;

    @Column(name="delFlag")
    private Integer delFlag;
    
    @Transient
    private List<Permission> childrens;
	
	public String toString() {
        return "Permission{" +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", descpt='" + descpt + '\'' +
                ", url='" + url + '\'' +
                ", delFlag=" + delFlag +
                ", childrens=" + childrens +
                '}';
    }
}