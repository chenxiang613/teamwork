package com.future.teamwork.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.future.teamwork.domain.User;

@Repository
public interface  UserDao  extends JpaRepository<User, Long> {
	
	User getByUserNameAndStatus(String userName,int status);
	
	User getByUserName(String userName);
	
    @Query(value="UPDATE user SET password=? WHERE user_name=?",nativeQuery=true)
    int updatePassword(String userName,String password);
    
    @Query(value="count u.id,o.id,ad.name "
    		+ "from user u, sys_order o , address ad "
    		+ "where u.id = o.user_id  and o.address_id = ad.id and u.status = ? "
    		+ "group by ad.id",nativeQuery=true)
    List<Object> selectOrderByAddress(int status,String password);
    
    
}
