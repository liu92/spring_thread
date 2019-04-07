package com.thread.comparetoandlist;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.thread.enumtype.EnumUtil;
import com.thread.enumtype.Ethnic;

public class DemoEnum {

	public static void main(String[] args) {
		
	

		Class<Ethnic> clasz=Ethnic.class;
		
//		String ethnicValue="HAN";
		
	    System.out.println(	Ethnic.valueOf(Ethnic.HAN.name()));
		
		
		Ethnic valueOf = Ethnic.valueOf(Ethnic.HAN.name());
		System.out.println("valueof==============="+valueOf);
		
		Object object = Ethnic.HAN;
		// 获取所有的方法
		Method[] declaredMethods = object.getClass().getDeclaredMethods();
		System.out.println(declaredMethods);
//		try {
			// 获取指定的方法,在获取枚举类中中指定方法时，如果是默认方法是无法指定的。如 枚举中没有 name方法，他只是 默认的一个方法所有无法指定
//			Method declaredMethod = object.getClass().getDeclaredMethod("name", null);
//			System.out.println(declaredMethod);
//		} catch (NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// 获取公有的方法
		Method[] methods = object.getClass().getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		System.out.println(methods);
		
		
		Object object2 = true;
		System.out.println("boolean类型=================="+object2);
		
		String string = "true";
		
		if(object2.equals(string)){
			System.out.println("-------------");
		}
		
		
		Map<Object, Object> enumToMap = EnumUtil.EnumToMap(clasz, object);
		
		System.out.println(JSON.toJSON(enumToMap));
		
		Boolean boolean1 = true;
		System.out.println(boolean1);
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		boolean isRepeat = list.size() != new HashSet<String>(list).size();
		System.out.println("list中包含重复元素：" + isRepeat);
	}
}
