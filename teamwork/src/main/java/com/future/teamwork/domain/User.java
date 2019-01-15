package com.future.teamwork.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="user")
public class User implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	
    public User() {
		super();
	}

	@Column(name="role_id")
    private Integer roleId;
    
    @Column(name="role_name")
    private String roleName;
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="password")
    private String password;
    	
    @Column(name="phone")
    private String phone;
    
    @Column(name="status")
    private Integer status;
    
    @Column(name="create_time")
    private String createTime;

    
    private String rememberMe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	 @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", userName='" + userName + '\'' +
	                ", password='" + password + '\'' +
	                ", roleId=" + roleId +
	                ", phone='" + phone + '\'' +
	                ", createTime='" + createTime + '\'' +
	                ", status=" + status +
	                '}';
	    }
}