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
@Table(name = "bis_station")
@EntityListeners(AuditingEntityListener.class)
public class Station implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// 地铁站名
	private String stationName;
	// 换乘站
	private Integer isInterchange;
	// 创建时间
	@CreatedDate
	private Date createDate;
	// 数据状态
	private Byte status = 1;

	@ManyToMany
	@JoinTable(name = "bis_station_prev_station", joinColumns = @JoinColumn(name = "station_id"), 
		inverseJoinColumns = @JoinColumn(name = "prev_station_id"))   
	private Set<Station> preStations;
	
	@ManyToMany
	@JoinTable(name = "bis_station_next_station", joinColumns = @JoinColumn(name = "station_id"), 
		inverseJoinColumns = @JoinColumn(name = "next_station_id"))   
	private Set<Station> nextStations;
	
	@ManyToMany
    @JoinTable(name = "bis_line_station", joinColumns = @JoinColumn(name = "station_id"), 
    	inverseJoinColumns = @JoinColumn(name = "line_id")) 
	private Set<Line> lines;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Integer getIsInterchange() {
		return isInterchange;
	}

	public void setIsInterchange(Integer isInterchange) {
		this.isInterchange = isInterchange;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Set<Station> getPreStations() {
		return preStations;
	}

	public void setPreStations(Set<Station> preStations) {
		this.preStations = preStations;
	}

	public Set<Station> getNextStations() {
		return nextStations;
	}

	public void setNextStations(Set<Station> nextStations) {
		this.nextStations = nextStations;
	}

	public Set<Line> getLines() {
		return lines;
	}

	public void setLines(Set<Line> lines) {
		this.lines = lines;
	}
	
}