package com.future.teamwork;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.future.teamwork.dao.UserDao;
import com.future.teamwork.domain.User;
import com.future.teamwork.utils.CopyUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamworkApplicationTests {

	   @Test
	    public void contextLoads() {
	    }

	    @Autowired
	    private UserDao userDao;

	    @Test
	    public void select() {
	        User test= new User();
	        test.setId(1);
	        test.setPhone("12345610086");
//	        User target = userDao.getOne(1);
//	        System.out.println(target.toString());
//	        BeanUtils.copyProperties(target,test);
//	        userDao.save(target);
	        
	        String[] params = CopyUtils.getNullPropertyNames(test);
	        for (String string : params) {
				System.out.println(string);
			}
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

