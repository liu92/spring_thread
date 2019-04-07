package com.thread.catchexception;

import java.util.concurrent.ThreadFactory;

/**
 * 解决 线程 异常逃逸问题。（一个线程的抛出异常之后就终止了如果再进行catch，都不在一个线程里面。怎么捕捉异常喃）
 * 
 * 这个时候我们得去实现UncaughtExceptionHandler这个接口来捕获抛出的异常。
 * UncaughtExceptionHandler是JDK5中的新接口
 * ，允许我们在每个线程上面都附加一个异常处理器，Thread.UncaughtExceptionHandler
 * .uncaughtException()方法会在线程因未捕捉的异常而在临近死亡时被调用
 * 。为了使用这个异常处理器我们创建一个新的线程工厂ThreadFactory。并在new线程时设置UncaughtExceptionHandler。
 * 
 * 实现ThreadFactory来创建线程，为每一个thread对象都附上一个未捕获异常的处理器
 * 
 * <p>
 * Title:HandlerThreadFactory
 * </p>
 * 
 * @author liuwanlin
 * @date 2018年3月21日下午3:54:04
 */
public class HandlerThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		System.out.println("当前类："+this+"：创建一个新线程");
		Thread thread= new Thread(r);
		System.out.println("创建线程="+thread);
		 //为每一个线程设置异常捕获器
		thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = " + thread.getUncaughtExceptionHandler());
		return thread;
	}

}
