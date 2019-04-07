package com.thread.startAndrun;

/**
 * 探究 start 和 run 方法的区别
 * <p>Title:StartDemo</p>
 * @author liuwanlin
 * @date 2017年12月29日下午5:13:59
 */
public class StartDemo extends Thread {

	public StartDemo(String name){
		super(name);
	}
	
	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName()+" is running");
	}
}
