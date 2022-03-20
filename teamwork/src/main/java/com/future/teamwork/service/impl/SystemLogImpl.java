package com.future.teamwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.teamwork.dao.system.SystemLogDao;
import com.future.teamwork.domain.SystemLog;
import com.future.teamwork.service.SystemLogService;

@Service
public class SystemLogImpl extends BaseServiceImpl<SystemLog, Long> implements SystemLogService {

	@Autowired
    private SystemLogDao systemLogDao;
   
}
