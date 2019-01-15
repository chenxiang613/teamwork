package com.future.teamwork;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.future.teamwork.test.domain.Test1;

import com.future.teamwork.test.dao.TestDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamworkApplicationTests {

	   @Test
	    public void contextLoads() {
	    }

	    @Autowired
	    private TestDao testUserDao;

	    @Test
	    public void insert() {
	        Test1 test= new Test1();
	        test.setId(1);
	        test.setName("zhangming");
//	        test.setPassword("23");
	        testUserDao.save(test);
	    }
//
//	    @Test
//	    public void update() {
//	        User user = new User();
//	        user.setId(1);
//	        user.setAge(18);
//	        user.setUsername("李四");
//	        testUserDao.save(user);
//	    }
//
//	    @Test
//	    public void select() {
//	        Optional<User> user = testUserDao.findById(1);
//	        System.out.println(user);
//	    }
//
//	    @Test
//	    public void delete() {
//	        testUserDao.deleteById(1);
//	    }
//	

}

