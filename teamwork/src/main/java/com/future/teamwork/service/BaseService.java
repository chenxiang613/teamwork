package com.future.teamwork.service;

import java.io.Serializable;

import com.future.teamwork.domain.Permission;
import com.future.teamwork.domain.Role;
import com.future.teamwork.domain.User;

public interface BaseService<T,ID extends Serializable> {
	
	public void delete(T entity);
	public void deleteById(ID id);
	public boolean existsById(ID id);
	public T saveOrUpdate(T entity);   
    User updateByUser(User user);
    Role updateByRole(Role role);
    Permission updateByPermission(Permission permission);
}
