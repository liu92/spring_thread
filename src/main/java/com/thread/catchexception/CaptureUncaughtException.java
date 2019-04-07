package com.thread.catchexception;

import java.util.concurrent.*;

/**
 * 测试捕获异常
 * <p>Title:CaptureUncaughtException</p>
 * @author liuwanlin
 * @date 2018年3月21日下午4:09:47
 */
public class CaptureUncaughtException {

	public static void main(String[] args) {
        ExecutorService service =Executors.newCachedThreadPool(new HandlerThreadFactory());
        service.execute(new ExceptionThread2());
        service.execute(new ExceptionThread3());

        service.shutdown();
//      ThreadFactory factory= Executors.defaultThreadFactory();

		// 当前类：com.thread.catchexception.HandlerThreadFactory@5bf6b81c：创建一个新线程
		// 创建线程=Thread[Thread-0,5,main]
		// eh = com.thread.catchexception.MyUncaughtExceptionHandler@665292e6
		// 运行线程名称 ====Thread-0
		// Exception
		// com.thread.catchexception.MyUncaughtExceptionHandler@665292e6

		// 当前类：com.thread.catchexception.HandlerThreadFactory@5bf6b81c：创建一个新线程
		// 创建线程=Thread[Thread-1,5,main]
		// eh = com.thread.catchexception.MyUncaughtExceptionHandler@71662a95
		// 当前类：com.thread.catchexception.HandlerThreadFactory@5bf6b81c：创建一个新线程
		//
		// [caugth:] : java.lang.RuntimeException
		// 创建线程=Thread[Thread-2,5,main]

		// eh = com.thread.catchexception.MyUncaughtExceptionHandler@6a688d6f
		// 运行线程名称 ==== Thread-2
		// Exception
		// com.thread.catchexception.MyUncaughtExceptionHandler@6a688d6f
		// 当前类：com.thread.catchexception.HandlerThreadFactory@5bf6b81c：创建一个新线程
		// 创建线程=Thread[Thread-3,5,main]
		// eh = com.thread.catchexception.MyUncaughtExceptionHandler@2ce62a39
		//
		// [caugth:] : java.lang.NullPointerException


	}
}
