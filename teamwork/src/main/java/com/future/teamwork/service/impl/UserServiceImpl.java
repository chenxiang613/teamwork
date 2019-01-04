package com.future.teamwork.service.impl;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.future.teamwork.domain.User;

 
@Service
public class UserServiceImpl extends JpaRepository<User, Integer>   {
	

//	@Override
//	public List<User> findAll() {
//		List<User> list = userDao.findAll();
//		userDao.save(new User());
//		return list;
//	}
//
//
//	@Override
//	public List<User> queryUserByName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}



}
