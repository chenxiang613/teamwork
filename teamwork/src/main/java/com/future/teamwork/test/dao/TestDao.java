package com.future.teamwork.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.future.teamwork.test.domain.Test1;

public interface TestDao extends JpaRepository<Test1, Integer> {

}
