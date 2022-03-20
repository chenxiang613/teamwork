package com.future.teamwork.dao.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.future.teamwork.domain.User;

@Mapper
public interface UserMapper {
	public boolean add(User user);

	public User findById(Long id);

	public List<User> findAll();
	
	public int insertBatch(List<User> userList);
}
