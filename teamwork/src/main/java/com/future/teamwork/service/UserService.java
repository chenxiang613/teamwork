package com.future.teamwork.service;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.future.teamwork.dao.UserRepository;
import com.future.teamwork.domain.User;

public interface UserService<T> extends JpaRepository<T, Serializable>{
	

}