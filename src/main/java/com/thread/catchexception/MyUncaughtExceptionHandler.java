package com.thread.catchexception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 	实现UncaughtExceptionHandler接口，抓住抛出的异常
 * <p>Title:MyUncaughtExceptionHandler</p>
 * @author liuwanlin
 * @date 2018年3月21日下午4:07:18
 */
public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("\n [caugth:] : "+e.toString());
	}

}
