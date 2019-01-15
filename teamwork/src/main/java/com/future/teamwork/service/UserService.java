package com.future.teamwork.service;

import java.util.Map;

import com.future.teamwork.domain.User;
import com.future.teamwork.utils.PageDataUtil;

public interface UserService extends BaseService<User, Integer>{
	
	PageDataUtil getUserList(User user, Integer pageNum, Integer pageSize);

    Map<String,Object> addUser(User user);

    Map<String,Object> updateUser(User user);

    User getUserById(Integer id);

    User findByUserName(String userName);

    int updatePassword(String userName,String password);

    Map<String, Object> delUser(Integer id,Integer status);

    Map<String, Object> recoverUser(Integer id,Integer status);

}