package com.future.teamwork.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="create_time")
    @CreatedDate
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @Column(name="update_time")
    @LastModifiedDate
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    
}
