package com.future.teamwork.domain.subway;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author 肖华
 * @date 2019/02/18
 */
@Entity
@Table(name="bis_line")
@EntityListeners(AuditingEntityListener.class)
public class Line implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// 线路名
	private String lineName;
	// 备注
	private String comment;
	// 数据状态
	private Byte status = 1;
	// 创建时间
	@CreatedDate
	private Date createDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bis_line_station", joinColumns = @JoinColumn(name = "line_id"), 
    	inverseJoinColumns = @JoinColumn(name = "station_id"))    
	private Set<Station> stations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Station> getStations() {
		return stations;
	}

	public void setStations(Set<Station> stations) {
		this.stations = stations;
	}
	
	
}

