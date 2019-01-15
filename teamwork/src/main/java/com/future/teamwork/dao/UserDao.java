package com.future.teamwork.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.future.teamwork.domain.User;

@Repository
public interface  UserDao  extends JpaRepository<User, Integer> {
	@Query(value="SELECT u.*,(SELECT role_name FROM role as r WHERE r.id = u.role_id) as role_name FROM user as u",nativeQuery=true)
	List<User> getUserList();
	
	@Query(value="SELECT * FROM user WHERE user_name = ? and status = 1 and id = ?",nativeQuery=true)
    User getUserByUserName(String userName,Integer id);

	@Modifying
	@Transactional
	@Query(value="UPDATE User SET status=:status WHERE id=:id")
    int updateUserStatus(@Param("id") Integer id,@Param("status") Integer status);
    
    @Query(value="UPDATE user SET user_name=?,password=?,role_name=?,phone=? WHERE id=?",nativeQuery=true)
    int updateUser(User user);
    
    @Query(value="SELECT * FROM user WHERE user_name = ? and status = 1",nativeQuery=true)
    User findByUserName(String userName);
    
    @Query(value="UPDATE user SET password=? WHERE user_name=?",nativeQuery=true)
    int updatePassword(String userName,String password);
    
    @Query(value="SELECT * FROM user WHERE id = ?",nativeQuery=true)
    User getById(Integer id);
}
