package com.thread.interrupt;

/**
 * 09:
 * interrupt()常常被用来终止“阻塞状态”线程
 * <p>Title:MyThread</p>
 * @author liuwanlin
 * @date 2018年1月2日下午5:04:42
 */
public class MyThread extends Thread{

	public MyThread(String name){
		super(name);
	}
	
	@Override
	public void run(){
		try {
			int i=0;
			System.out.println("try -->start="+isInterrupted());
			while (!isInterrupted()) {
				System.out.println("try -->while="+isInterrupted());
				// 休眠100ms
				Thread.sleep(100);
				i++;
				System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") loop "+i);
//				break;
				return;
			}
		} catch (InterruptedException e) {
			 System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
		}
	}
}
