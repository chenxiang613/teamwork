package com.future.teamwork.service;

import java.util.Map;

import com.future.teamwork.domain.User;

public interface UserService extends BaseService<User, Integer>{
	
    Map<String, Object> updateUser(User user);

    User findByUserName(String userName);

    int updatePassword(String userName,String password);

    Map<String, Object> delUser(Integer id,Integer status);

    Map<String, Object> recoverUser(Integer id,Integer status);
    
}