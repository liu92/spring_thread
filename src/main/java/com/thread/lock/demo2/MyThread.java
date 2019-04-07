package com.thread.lock.demo2;

/**
 *  将 MyThread 改为进程Thread
 *  
 * <p>Title:MyThread</p>
 * @author liuwanlin
 * @date 2017年8月15日下午2:06:26
 */
public class MyThread extends Thread {

	public  MyThread(String name) {
        super(name);
	}
	
	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName()+" loop"+i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

   
	
}
