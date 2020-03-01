package com.thread.yield;
/**
 * yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，
 * 从而让其它具有相同优先级的等待线程获取执行权；
 * 但是，并不能保证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；
 * 也有可能是当前线程又进入到“运行状态”继续运行！
 * <p>Title:ThreadYield</p>
 * @author liuwanlin
 * @date 2018年1月2日上午11:01:16
 */
public class ThreadYield extends Thread{
	 public ThreadYield(String name){
		 super(name);
	 }
	 
	 
	 @Override
	 public synchronized void run(){
		 for (int i = 0; i < 10; i++) {
			System.out.printf("%s [%d]:%d\n",this.getName(),this.getPriority(),i);
			// i整除4时，调用yield
			if(i%4==0){
				System.out.println("当前线程的开始状态-------------"+Thread.currentThread().getState());
				Thread.yield();
				System.out.println("当前线程执行yield后状态-------------"+Thread.currentThread().getState());
				System.out.println(i+"线程让步="+this.getName());
			}
		}
	 }
}
