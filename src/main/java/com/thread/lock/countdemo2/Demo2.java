package com.thread.lock.countdemo2;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 第二条：当一个线程访问“某对象”的“synchronized方法”
 * 或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块。
 * <p>
 * Title:Demo2
 * </p>
 * 
 * @author liuwanlin
 * @date 2017年8月15日下午2:46:53
 * @see CountDemo2
 */
public class Demo2 {

	public static void main(String[] args) {
		// 这里 使用final 是因为 匿名内部类只能访问final类型变量
		final CountDemo2 count = new CountDemo2();


	/**
	 *
	 * 使用线程池来创建线程
	 */

	ExecutorService executorService = new ThreadPoolExecutor(5,50,
			200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(1024),
			new DefaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

		executorService.execute(count::SynMethod);

    executorService.execute(count::noSynMethod);


    executorService.shutdown();


    /**
		// 新建t1, t1会调用“count对象”的synMethod()方法
	//	Thread t1 = new Thread(() ->count.SynMethod(), "t1");

	//	Thread t2 = new Thread(() ->count.noSynMethod(), "t2");

	//	t1.start();
	//	t2.start();
	 */

	}
	/**
	 * // 结果
	// t1 synMethod loop 0
	// t2 noSynMethod loop 0
	// t2 noSynMethod loop 1
	// t1 synMethod loop 1
	// t1 synMethod loop 2
	// t2 noSynMethod loop 2
	// t2 noSynMethod loop 3
	// t1 synMethod loop 3
	// t1 synMethod loop 4
	// t2 noSynMethod loop 4

	// 主线程中创建了两个子线程t1和t2，t1调用count对象的SynMethod的方法，该方法含有同步块；而t2则会调用count对象中noSynMethod方法，但是该方法中不含有同步块，
	// t1 运行时，虽然调用 synchronized
	// (this)获取"count的同步锁";但是并没有造成t2的阻塞，因为t2没有用到"count同步锁"


	 以下使用 线程池来创建 测试结果一直
	 同步块 CountDemo2当前线程=pool-1-thread1 synMethod loop 0
	 非同步 CountDemo2当前线程=pool-1-thread2 noSynMethod loop 0
	 同步块 CountDemo2当前线程=pool-1-thread1 synMethod loop 1
	 非同步 CountDemo2当前线程=pool-1-thread2 noSynMethod loop 1
	 同步块 CountDemo2当前线程=pool-1-thread1 synMethod loop 2
	 非同步 CountDemo2当前线程=pool-1-thread2 noSynMethod loop 2
	 同步块 CountDemo2当前线程=pool-1-thread1 synMethod loop 3
	 非同步 CountDemo2当前线程=pool-1-thread2 noSynMethod loop 3
	 同步块 CountDemo2当前线程=pool-1-thread1 synMethod loop 4
	 非同步 CountDemo2当前线程=pool-1-thread2 noSynMethod loop 4
   */
}
