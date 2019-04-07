package com.thread.lock.demosomething;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测 //	(02) x.isSyncA()与y.isSyncA()
 * <p>Title:LockDemo2</p>
 * @author liuwanlin
 * @date 2017年8月21日下午2:41:23
 */
public class LockDemo2Something {

	// 可以同时被访问。
	// 因为访问的不是同一个对象的同步锁，x.isSyncA()访问的是x的同步锁，而y.isSyncA()访问的是y的同步锁。
	
	
	Something x =new Something();
	
	Something y =new Something();
	
	private void test2(){
		ExecutorService executorService = new ThreadPoolExecutor(5,
				50, 200,
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

		executorService.execute(() -> x.isSyncA());

		executorService.execute(() -> y.isSyncB());

		executorService.shutdown();
		/**
		//Thread t21=new Thread(() -> x.isSyncA(),"t21");
		//
		//Thread t22 = new Thread(() -> y.isSyncB(),"t22");
		//
		//
		//t21.start();
		//t22.start();
		 */
	}
	
	public static void main(String[] args) {
		LockDemo2Something demo2= new LockDemo2Something();
		demo2.test2();
	}

	/**
	// 结果  可以看出因为访问的不是同一个对象锁，所以出现了异步的情况。
	//	t22  : isSynB
	//	t21  : isSynA
	//	t21  : isSynA
	//	t22  : isSynB
	//	t22  : isSynB
	//	t21  : isSynA
	//	t22  : isSynB
	//	t21  : isSynA
	//	t21  : isSynA
	//	t22  : isSynB

	 使用线程池创建 执行结果
	 pool-1-thread2  : isSynB
	 pool-1-thread1  : isSynA
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : isSynB
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : isSynB
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : isSynB
	 pool-1-thread2  : isSynB
	 pool-1-thread1  : isSynA

	*/

	
}
