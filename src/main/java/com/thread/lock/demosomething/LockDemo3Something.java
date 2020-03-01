package com.thread.lock.demosomething;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试（03）因为cSyncA()和cSyncB()都是static类型，x.cSyncA()相当于Something.isSyncA()，
 * y.cSyncB()相当于Something.isSyncB()，因此它们共用一个同步锁，不能被同时反问。
 * <p>Title:LockDemo3</p>
 * @author liuwanlin
 * @date 2017年8月21日下午4:05:36
 */
public class LockDemo3Something {


	/** // 比较(03) x.cSyncA()与y.cSyncB()*/
	private void test3(){

		ExecutorService executorService = new ThreadPoolExecutor(2,
				5, 200,
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(6),
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

		executorService.execute(Something::cSyncA);
//        executorService.execute(Something::cSyncB);

//        executorService.shutdown();
		/**
		 *      lamda 第一种写法
		//	executorService.execute(()->Something.cSyncA());

		       以前写法
		//  Thread t31= new Thread(() -> Something.cSyncA(),"t31");
		//
		//
		//	Thread t32=new Thread(() ->Something.cSyncB(),"t32");
		//
		//	t31.start();
		//	t32.start();*/
	}
	
	
	public static void main(String[] args) {
		LockDemo3Something demo3= new LockDemo3Something();
		demo3.test3();
	}

	/**
	//结果：x.cSyncA()相当于Something.isSyncA()，y.cSyncB()相当于Something.isSyncB()，因此他们共用一个同步锁，所以不能同时访问
	
	//	t31  : cSyncA
	//	t31  : cSyncA
	//	t31  : cSyncA
	//	t31  : cSyncA
	//	t31  : cSyncA
	//	t32  : cSyncB
	//	t32  : cSyncB
	//	t32  : cSyncB
	//	t32  : cSyncB


	 使用线程池 来创建 线程，执行结果如下
	 pool-1-thread1  : cSyncA
	 pool-1-thread1  : cSyncA
	 pool-1-thread1  : cSyncA
	 pool-1-thread1  : cSyncA
	 pool-1-thread1  : cSyncA
	 pool-1-thread2  : cSyncB
	 pool-1-thread2  : cSyncB
	 pool-1-thread2  : cSyncB
	 pool-1-thread2  : cSyncB
	 pool-1-thread2  : cSyncB

	 */
}
