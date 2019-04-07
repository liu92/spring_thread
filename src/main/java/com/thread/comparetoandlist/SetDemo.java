package com.thread.comparetoandlist;

import java.util.HashSet;
import java.util.Set;

  /**
     *  java类简单作用描述
     * @ProjectName:    SetDemo.java
     * @Package:        com.thread.comparetoandlist
     * @ClassName:      SetDemo
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/22 0022 17:51
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/22 0022 17:51
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class SetDemo {
  public static void main(String[] args) {
	  Set<String> set1 = new HashSet<String>();
	  set1.add("aa");
	  set1.add("bb");
	  set1.add("cc");
	  
	  
	  Set<String> set2 = new HashSet<String>();
	  set2.add("cc");
	  set2.add("bb");
	  set2.add("aa");
	 
	 System.out.println(set1.containsAll(set2));
	 
  }
  
  
  

}
