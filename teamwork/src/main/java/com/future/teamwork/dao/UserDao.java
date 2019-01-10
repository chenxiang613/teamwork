package com.future.teamwork.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.User;

@Repository
public interface  UserDao  extends JpaRepository<User, Integer> {
	@Query(value="SELECT * FROM user",nativeQuery=true)
	List<User> getUserList(User user);
	
	@Query(value="SELECT * FROM user WHERE user_name = ? and status = 1 and id = ?",nativeQuery=true)
    User getUserByUserName(String userName,Integer id);

	@Query(value="UPDATE user SET status=? WHERE id=?",nativeQuery=true)
    int updateStatus(Integer id,Integer status);
    
    @Query(value="UPDATE user SET user_name=?,password=?,rold_id=?,phone=? WHERE id = ?",nativeQuery=true)
    int updateUser(User user);
    
    @Query(value="SELECT * FROM user WHERE user_name = ? and status = 1",nativeQuery=true)
    User findByUserName(String userName);
    
    @Query(value="UPDATE user SET password=? WHERE user_name=?",nativeQuery=true)
    int updatePassword(String userName,String password);
    
    @Query(value="SELECT * FROM user WHERE id = ?",nativeQuery=true)
    User getById(Integer id);
}
