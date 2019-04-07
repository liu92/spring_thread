package com.thread.lock.demo1;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * 测试第一条
 * <p>
 * Title:Demo_1
 * </p>
 * 
 * @author liuwanlin
 * @date 2017年8月15日下午1:07:13
 *       2018年11月19日 修改使用线程池创建线程
 */
public class Demo1To1 {
	public static void main(String[] args) {
		// 新建“Runnable对象”
		Runnable demo = new Myrunable();
		// 新建“线程t1”,
//		Thread thread1 = new Thread(demo, "线程名称 =t1");
		// 新建“线程t2”,
//		Thread thread2 = new Thread(demo, "线程名称 =t2");

		 ExecutorService executorService = new ThreadPoolExecutor(5,
				 50, 200,
				 TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
				 new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());



		/**线程一*/
		executorService.execute(demo);
		/**线程二*/
		executorService.execute(demo);

		executorService.shutdown();

		/**
		 // t1是基于demo这个Runnable对象
		 // 新建“线程t2”,
		 //		Thread thread2 = new Thread(demo, "t2");
		 // t2是基于demo这个Runnable对象
		 //		thread1.start();
		 //		thread2.start();
		 */

	}
	/**
	// 结果
	//	t1 loop0
	//	t1 loop1
	//	t1 loop2
	//	t1 loop3
	//	t1 loop4
	//	t2 loop0
	//	t2 loop1
	//	t2 loop2
	//	t2 loop3
	//	t2 loop4

	// 结果说明：run()方法中存在“synchronized(this)代码块”，
	// 而且t1和t2都是基于"demo这个Runnable对象"创建的线程。这就意味着，
	// 我们可以将synchronized(this)中的this看作是“demo这个Runnable对象”；
	// 因此，线程t1和t2共享“demo对象的同步锁”。
	// 所以，当一个线程运行的时候，另外一个线程必须等待“运行线程”释放“demo的同步锁”之后才能运行。


	 同样的改为线程池使用 如果是同一个对象 ，也会依次执行，
	//	 pool-1-thread1 loop0
	//	 pool-1-thread1 loop1
	//	 pool-1-thread1 loop2
	//	 pool-1-thread1 loop3
	//	 pool-1-thread1 loop4
	//	 pool-1-thread2 loop0
	//	 pool-1-thread2 loop1
	//	 pool-1-thread2 loop2
	//	 pool-1-thread2 loop3
	//	 pool-1-thread2 loop4

	

	 */
}
