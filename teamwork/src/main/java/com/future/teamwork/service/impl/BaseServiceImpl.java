package com.future.teamwork.service.impl;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.Role;
import com.future.teamwork.domain.User;
import com.future.teamwork.service.BaseService;

public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T,ID>{

	@Autowired
	private JpaRepository<T, ID> jpaRepository;
	
	@Autowired
	private JpaRepository<User, Integer> userRepository;
	
	@Autowired
	private JpaRepository<Role, Integer> roleRepository;
	
	@Autowired
	private JpaRepository<Permission, Integer> permissionRepository;
	
	@Override
	public void delete(T entity) {
		jpaRepository.delete(entity);
		
	}

	@Override
	public void deleteById(ID id) {
		jpaRepository.deleteById(id);		
	}

	@Override
	public boolean existsById(ID id) {
		return jpaRepository.existsById(id);
	}

	@Override
	public T saveOrUpdate(T entity) {
//		jpaRepository.findOne(id);
		return jpaRepository.save(entity);
	}

	@Override
	public User updateByUser(User user) {
		if (user.getId() == null) {
	    	   return userRepository.save(user);
	    	   }
	       User saveUser = userRepository.getOne(user.getId());
	       BeanUtils.copyProperties(user, saveUser, "status", "roleName","createTime");
	       return userRepository.saveAndFlush(saveUser);
	}

	@Override
	public Role updateByRole(Role role) {
		if (role.getId() == null) {
	    	   return roleRepository.save(role);
	    	   }
	       Role saveRole = roleRepository.getOne(role.getId());
	       BeanUtils.copyProperties(role, saveRole, "status", "roleName","createTime");
	       return roleRepository.saveAndFlush(saveRole);
	}

	@Override
	public Permission updateByPermission(Permission permission) {
		if(permission.getId() == null){
			return permissionRepository.save(permission);
		}
		Permission savePermission = permissionRepository.getOne(permission.getId());
		BeanUtils.copyProperties(permission, savePermission, "createTime","delFlag");
		return permissionRepository.saveAndFlush(savePermission);
	}




}
