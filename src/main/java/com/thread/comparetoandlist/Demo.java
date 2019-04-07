package com.thread.comparetoandlist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.thread.util.DateTool;
  /**
     *  java类简单作用描述
     * @ProjectName:    Demo.java
     * @Package:        com.thread.comparetoandlist
     * @ClassName:      Demo
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/10/30 0030 14:44
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/10/30 0030 14:44
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class Demo {

	 public static void main(String[] args) {
		List<String> list =new ArrayList<String>();
		
		list.add("AAAA");
		list.add("AAAA");
		list.add("BBBB");
		list.add("CCCC");
		list.add("DDDD");
		list.add("AAAA");
		list.add("BBBB");
		
		
		List<String> result = new ArrayList<>();
		 
		for(String s: list){
		    if(Collections.frequency(result, s) < 1) {
				result.add(s);
			}
		}
		
		System.out.println(result);
		
		
		String t="12132";
		String[] p = t.split(",");
		boolean i = t.contains(",");
		System.out.println(p);
		System.out.println(i);
		
		
		
		String pre = DateTool.dateToString(DateTool.getCurrentDate(), DateTool.DATE_PATTERN_STRING);

		String tail = String.format("%06d", new Random().nextInt(100000));


		
		SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		try {
			// 1
			Date date = date2.parse("2018-07-01 09:06:59");
			
			Date startDate = date2.parse("2018-07-01 00:00:00");
			
			// 2
			Date date1 = date2.parse("2018-06-30 23:59:59");
			
			Date startDate1 = date2.parse("2018-06-30 23:59:59");
			
			// 大于
			if(date.compareTo(startDate) == 1){
				System.out.println("第一种22222");
			}
			// 小于
			else if(date.compareTo(startDate) == -1){
				System.out.println("第一种33333");
			}
			// 等于
			else if(date.compareTo(startDate) == 0){
				System.out.println("第一种 4444444444");
			}
			
			
			if(date1.compareTo(startDate1) == 1){
				System.out.println("第二种555");
			}else if(date1.compareTo(startDate1) == -1){
				System.out.println("第二种666");
			}else if(date1.compareTo(startDate1) == 0){
				System.out.println("第二种777");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		[AAAA, BBBB, CCCC, DDDD]
	}
}
