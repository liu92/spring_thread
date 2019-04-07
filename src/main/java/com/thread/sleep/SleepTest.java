package com.thread.sleep;

/**
 * 演示sleep()的用法。
 * 
 * <p>Title:SleepTest</p>
 * @author liuwanlin
 * @date 2018年1月2日下午12:09:50
 */
public class SleepTest {
  public static void main(String[] args) {
	ThreadSleep t1=new ThreadSleep("t1");
    t1.start();
  }
}
