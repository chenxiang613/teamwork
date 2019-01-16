package com.future.teamwork.utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;

public class CopyUtils {
	public static <T> String[] getNullPropertyNames(T source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
	public static <T> Field[] testReflect(T source){
		Class<?> c = source.getClass();
		System.out.println(c.getName());
		Field[] paramsArray = c.getDeclaredFields();
		for (Field field : paramsArray) {
			System.out.println(field.getName());
		}
		return paramsArray;
	}
	
	public static <T> PageDataUtil coyp(Page<T> source){
		PageDataUtil pdu = new PageDataUtil();
		if( source.getContent() !=null ){
			pdu.setList(source.getContent());
			pdu.setCode(200);
			pdu.setTotals(Integer.valueOf((int)source.getTotalElements()));
		}
		return pdu;
	}
	
    public static void main(String[] args) {
    	int a = 3;
    	System.out.println(a > 2? true:false);
    }
}
