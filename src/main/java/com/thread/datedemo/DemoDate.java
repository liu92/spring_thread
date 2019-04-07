package com.thread.datedemo;

import java.util.Date;

import com.thread.util.DateTool;
  /**
     *  java类简单作用描述
     * @ProjectName:    DemoDate.java
     * @Package:        com.thread.datedemo
     * @ClassName:      DemoDate
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/10/30 0030 14:43
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/10/30 0030 14:43
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class DemoDate {
 public static void main(String[] args) {
	 
	 
	 Date remindEndDate = DateTool.getEndDateTime(DateTool.getNextDay(new Date(), 5));	
	 System.out.println(remindEndDate);
 }
}
