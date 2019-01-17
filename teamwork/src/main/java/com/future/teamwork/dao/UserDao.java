package com.future.teamwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.User;

@Repository
public interface  UserDao  extends JpaRepository<User, Integer> {
	
	User getByUserNameAndStatus(String userName,int status);
	
	User getByUserName(String userName);

    @Query(value="UPDATE user SET password=? WHERE user_name=?",nativeQuery=true)
    int updatePassword(String userName,String password);
    
}
