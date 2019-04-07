package com.thread.interrupt;

/**
 * 将run()方法中捕获InterruptedException异常的代码块移到while循环体内
 * <p>Title:MyThread1</p>
 * @author liuwanlin
 * @date 2018年1月3日上午11:01:08
 */
public class MyThread1 extends Thread{

	public MyThread1(String name){
		super(name);
	}
	
	
	@Override
	public void run(){
		int i=0;
		System.out.println("try -->start="+isInterrupted());
		while (!isInterrupted()) {
			System.out.println("try -->while="+isInterrupted());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				 System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
			}
			 i++;
	         System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") loop " + i);  
		}
	}
}
