package com.future.teamwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.teamwork.dao.UserDao;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.UserService;
import com.future.teamwork.utils.DateUtil;
import com.future.teamwork.utils.DigestUtil;
import com.future.teamwork.utils.StaticConstantUtils;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User save(User user) {
        User old = userDao.getByUserName(user.getUserName());
        if(old != null){
            return null;
        }
        String password = DigestUtil.Md5(user.getUserName(),user.getPassword());
        user.setPassword(password);
        user.setCreateTime(DateUtil.getCurrentDate());
        user.setStatus(1);
        return super.save(user);
    }


    @Override
    public User updateUser(User user) {
        Integer id = user.getId();
        User old = userDao.getByUserName(user.getUserName());
        if(old != null){
        	return null;
        }
        String username = user.getUserName();
        if(user.getPassword() != null){
            String password = DigestUtil.Md5(username,user.getPassword());
            user.setPassword(password);
        }
        return super.update(user, user.getId());
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.getByUserNameAndStatus(userName,StaticConstantUtils.System_Status_Enable);
    }


    @Override
    public int updatePassword(String userName, String password) {
        password = DigestUtil.Md5(userName,password);
        return userDao.updatePassword(userName,password);
    }
}
