package com.future.teamwork.service;

import com.future.teamwork.domain.User;

public interface UserService extends BaseService<User, Integer>{
	
    User updateUser(User user);

    User findByUserName(String userName);

    int updatePassword(String userName,String password);

    
}