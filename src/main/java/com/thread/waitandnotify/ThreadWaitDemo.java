package com.thread.waitandnotify;

/**
 * 1 对应 4
 * 测试线程等待和唤醒
 * 
 * 在Object.java中，定义了wait(), notify()和notifyAll()等接口。
 * wait()的作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁
 * 而notify()和notifyAll()的作用，则是唤醒当前对象上的等待线程；notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程
 * <p>Title:ThreadA</p>
 * @author liuwanlin
 * @date 2017年8月21日下午14:51:43
 */
public class ThreadWaitDemo extends Thread{
	
	public ThreadWaitDemo(String name){
		super(name);
	}

	@Override
	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+" call notify");
			//唤醒当前的wait等待线程
//		   notify();
		}
	}
	
   
}
