package com.thread.lock.demosomething;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试 (04) x.isSyncA()与Something.cSyncA()
 * <p>
 * Title:LockDemo4
 * </p>
 * 
 * @author liuwanlin
 * @date 2017年8月21日下午4:15:38
 */
public class LockDemo4Something {

	Something x = new Something();


	private void test4() {

		ExecutorService executorService = new ThreadPoolExecutor(5,
				50, 200,
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

		executorService.execute(()->x.isSyncA());
		executorService.execute(Something::cSyncA);
		executorService.shutdown();


		/**
		 *
		 *
		 * 不使用 线程池创建线程
		//Thread t41 = new Thread(() ->x.isSyncA(), "t41");
		//
		//Thread t42 = new Thread(() ->Something.cSyncA(), "t42");
		//t41.start();
		//t42.start();

		*/
	}

	public static void main(String[] args) {
		LockDemo4Something demo4 = new LockDemo4Something();
		demo4.test4();
	}
	/**
	// t41 : isSynA
	// t42 : cSyncA
	// t41 : isSynA
	// t42 : cSyncA
	// t41 : isSynA
	// t42 : cSyncA
	// t41 : isSynA
	// t42 : cSyncA
	// t41 : isSynA
	// t42 : cSyncA

	// 结果说明
	// 因为isSyncA()是实例方法，x.isSyncA()使用的是对象x的锁；
	// 而cSyncA()是静态方法，Something.cSyncA()可以理解对使用的是“类的锁”。因此，它们是可以被同时访问的。


       线程池创建线程 ，执行结果
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : cSyncA
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : cSyncA
	 pool-1-thread2  : cSyncA
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : cSyncA
	 pool-1-thread1  : isSynA
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : cSyncA


	 */

}
