package com.thread.lock.countdemo3;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 将noSynMethod方法也改成 synchronized(this)修饰
 * <p>
 * Title:Demo3
 * </p>
 * 
 * @author liuwanlin
 * @date 2017年8月16日上午10:40:52
 * @see 
 */
public class Demo3 {
	public static void main(String[] args) {
		final Count1Demo3 count1 = new Count1Demo3();
		// 新建t1, t1会调用“count对象”的synMethod()方法
//		Thread t1 = new Thread(()->count1.SynMethod(), "t1");
//
//		Thread t2 = new Thread(() ->count1.noSynMethod(), "t2");

//		t1.start();
//		t2.start();


		ExecutorService executorService = new ThreadPoolExecutor(5,50,
				200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(1024),
				new DefaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

		executorService.execute(count1::SynMethod);

		executorService.execute(count1::noSynMethod);

		executorService.shutdown();
	}
	/**
	// 结果
	// t1 synMethod loop 0
	// t1 synMethod loop 1
	// t1 synMethod loop 2
	// t1 synMethod loop 3
	// t1 synMethod loop 4
	// t2 noSynMethod loop 0
	// t2 noSynMethod loop 1
	// t2 noSynMethod loop 2
	// t2 noSynMethod loop 3
	// t2 noSynMethod loop 4

	// 从结果可以看出 主线程 创建了两个子线程t1和t2,t1和t2在运行时都会调用 synchronized (this) ,
	// 而 this 指的是 当前对象  count1，而且t1和t2共用一个对象，、
	// 因此 在t1运行是时，count1同步锁被t1使用，t2被阻塞，要等待t1释放锁之后，t2才能获取到。


	 同样的线程池创建两个线程 但是调用的count1 都是同一个对象，
	 所有在第一个线程调用 synMethod 方法是 同步锁被count1使用，
	 后面noSynMethod 需要等待

	 Count1Demo3当前线程=pool-1-thread1 synMethod loop 0
	 Count1Demo3当前线程=pool-1-thread1 synMethod loop 1
	 Count1Demo3当前线程=pool-1-thread1 synMethod loop 2
	 Count1Demo3当前线程=pool-1-thread1 synMethod loop 3
	 Count1Demo3当前线程=pool-1-thread1 synMethod loop 4
	 Count1Demo3当前线程=pool-1-thread2 noSynMethod loop 0
	 Count1Demo3当前线程=pool-1-thread2 noSynMethod loop 1
	 Count1Demo3当前线程=pool-1-thread2 noSynMethod loop 2
	 Count1Demo3当前线程=pool-1-thread2 noSynMethod loop 3
	 Count1Demo3当前线程=pool-1-thread2 noSynMethod loop 4


	 */
}
