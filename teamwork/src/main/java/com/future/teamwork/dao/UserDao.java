package com.future.teamwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.future.teamwork.domain.User;

public interface  UserDao  extends JpaRepository<User, Integer> {

}
