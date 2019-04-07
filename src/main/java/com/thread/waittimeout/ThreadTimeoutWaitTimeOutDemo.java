package com.thread.waittimeout;

/**
 * 2对应4
 * 测试timeout
 * 
 * <p>Title:ThreadTimeout</p>
 * @author liuwanlin
 * @date 2017年8月21日下午16:30:45
 */
public class ThreadTimeoutWaitTimeOutDemo extends Thread{

	public ThreadTimeoutWaitTimeOutDemo(String name){
		super(name);
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" run");
		/**死循环 ，不断运行 */
		while (true) {

		}
	}
	
	
}
