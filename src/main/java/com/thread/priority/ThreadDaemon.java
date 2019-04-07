package com.thread.priority;

/**
 * 设置  守护线程
 * <p>Title:ThreadDaemon</p>
 * @author liuwanlin
 * @date 2018年1月5日下午6:00:58
 */
public class ThreadDaemon extends Thread{

	public ThreadDaemon(String name){
		super(name);
	}
	
	public void run(){
			try {
				for (int i = 0; i < 5; i++) {
					Thread.sleep(3);
					 System.out.println(this.getName() +"((是否为守护线程)isDaemon="+this.isDaemon()+ ")" +", loop "+i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
}
