package com.thread.priority;

/**
 * 线程优先级 
 * <p>Title:MyThread</p>
 * @author liuwanlin
 * @date 2018年1月5日下午5:40:52
 */
public class MyThread extends Thread {

	public MyThread(String name){
		super(name);
	}
	
	
	public void run(){
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()
                    +"("+Thread.currentThread().getPriority()+ ")"
                    +", loop "+i);
			
			
			
//			System.out.println("name="+Thread.currentThread().getName()+
//					"  \n  ClassLoader="+Thread.currentThread().getContextClassLoader()+
//					"  \n StackTrace="+Thread.currentThread().getStackTrace()+"   \n Group="+
//					Thread.currentThread().getThreadGroup());
			
			System.out.println("===============================");
		}
	}
}
