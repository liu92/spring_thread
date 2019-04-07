package com.thread.catchexception;
/**
 * 这里创建抛出异常的线程类
 * <p>Title:ExceptionThread2</p>
 * @author liuwanlin
 * @date 2018年3月21日下午4:01:08
 */
public class ExceptionThread2 implements Runnable {

	@Override
	public void run() {
         Thread thread =Thread.currentThread();
         System.out.println("运行线程名称 ===="+thread.getName());
         System.out.println("Exception "+thread.getUncaughtExceptionHandler());
         throw new RuntimeException();
	}

}
