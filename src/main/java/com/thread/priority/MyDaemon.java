package com.thread.priority;

/**
 * 设置守护线程
 * <p>Title:MyDaemon</p>
 * @author liuwanlin
 * @date 2018年1月11日下午5:38:37
 */
public class MyDaemon extends Thread{

	public MyDaemon(String name){
	    super(name);
	}
	
	
	public void run(){
		 try {
            for (int i=0; i<10000; i++) {
                Thread.sleep(1);
                System.out.println(this.getName() +"(isDaemon="+this.isDaemon()+ ")" +", loop "+i);
            }
          } catch (InterruptedException e) {
         }
	}
}
