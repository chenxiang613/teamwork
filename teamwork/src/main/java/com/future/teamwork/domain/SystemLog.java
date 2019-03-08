package com.future.teamwork.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "system_log")
@EntityListeners(AuditingEntityListener.class)
@Data
public class SystemLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3831392621132055218L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private String method;
	private Long logType;
	private String requestIp;
	private String exceptioncode;
	private String exceptionDetail;
	private String params;
	private String createBy;

	@Column(name = "create_time")
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

}