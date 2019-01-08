package com.future.teamwork;

import java.util.Optional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.future.teamwork.dao.UserDao;
import com.future.teamwork.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamworkApplicationTests {

	   @Test
	    public void contextLoads() {
	    }

	    @Autowired
	    private UserDao testUserDao;

	    @Test
	    public void insert() {
	        User user= new User();
	        user.setUsername("zhang san");
	        user.setAge(23);
	        testUserDao.save(user);
	    }

	    @Test
	    public void update() {
	        User user = new User();
	        user.setId(1);
	        user.setAge(18);
	        user.setUsername("李四");
	        testUserDao.save(user);
	    }

	    @Test
	    public void select() {
	        Optional<User> user = testUserDao.findById(1);
	        System.out.println(user);
	    }

	    @Test
	    public void delete() {
	        testUserDao.deleteById(1);
	    }
	

}

