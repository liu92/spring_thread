package com.thread.sleep;

/**
 * 07 : 线程休眠
 * 
 * sleep() 的作用是让当前线程休眠，即当前线程会从“运行状态”进入到“休眠(阻塞)状态”。
 * sleep()会指定休眠时间，线程休眠的时间会大于/等于该休眠时间；在线程重新被唤醒时，它会由“阻塞状态”变成“就绪状态”，从而等待cpu的调度执行。
 * 
 * <p>Title:ThreadSleep</p>
 * @author liuwanlin
 * @date 2018年1月2日上午11:53:27
 */
public class ThreadSleep extends Thread{

	public ThreadSleep(String name){
		super(name);
	}
	
	
	@Override
	public synchronized void run(){
		try {
			for (int i = 0; i < 10; i++) {
				System.out.printf("sleep %s: %d\n",this.getName(),i);
				// i整除4时，调用yield
				if(i%4==0){
					Thread.sleep(100);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
