package com.thread.callable;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * 测试 Callable 和 Future 创建线程
 * @ClassName: ThreadTest

 * @Description:测试 Callable 和 Future 创建线程
 * @author liuwl
 * @date 2018年8月14日下午3:34:26
 */
public class ThreadTest {
	public static void main(String[] args) {
		//创建 myCallable对象
		Callable<Integer> myCallable = new MyCallable();

		/**
		//  FutureTask 类的定义 是， FutureTask类实现了RunnableFuture<V> 接口，我们看一下 RunnableFuture<V> 接口的实现：
	    //  于是，我们发现 RunnableFuture<V> 类实际上是同时实现了Runnable和Future接口，由此才使得其具有Future和Runnable双重特性
		// 。通过Runnable特性，可以作为Thread对象的target，而Future特性，使得其可以取得新创建线程中的call()方法的返回值。
		//使用FutureTask来包装MyCallable对象
		 */
		FutureTask<Integer> future = new FutureTask<>(myCallable);
		
		/**
		// 多态的体现
		//FutureTask 对象作为 Thread对象的target创建新的线程
		 */
//		Thread thread = new Thread(future);
		ExecutorService executorService = new ThreadPoolExecutor(5,20,
				200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
				new DefaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());


        executorService.execute(future);
        executorService.shutdown();
//		thread.start(); //线程进入到就绪状态
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("主线程执行任务");
		
		
		try {
			//取得新创建的线程中的call()方法返回的结果
			int sum = future.get();
			  System.out.println("task运行结果,sum = " + sum);
		} catch (Exception e) {
			  e.printStackTrace();
		}
		
		System.out.println("所有任务执行完毕");
	}
}
