package com.thread.lock.demosomething;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 *  测试"实例锁" 和 "全局锁"
 * 实例锁：锁在某一个实例对象上。如果该类是单例，那么该锁也具有全局锁的概念。实例锁对应的就是synchronized关键字
 * 全局锁：该锁针对的是类，无论实例多少个对象，那么线程都共享该锁。
 * 全局锁对应的就是static synchronized（或者是锁在该类的class或者classloader对象上）。
 * 
 * <p>Title:LockDemo1</p>
 * @author liuwanlin
 * @date 2017年8月21日下午1:17:05
 */
public class LockDemo1Something {

	Something x =new Something();
	
	Something y =new Something();

	/**
	 *   不能被同时访问。
	 */
	private void Test1(){

		ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
		ExecutorService executorService = new ThreadPoolExecutor(5,
				10, 200,
				TimeUnit.MILLISECONDS, arrayBlockingQueue,
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        int  t =100;

		long a = 0;

		for(int i=0;i<t;i++){
			MyTask myTask = new MyTask(i);
			try{
				a= System.currentTimeMillis();
				executorService.execute(myTask);
				if(arrayBlockingQueue.size()>0){
					System.out.println("被阻塞的线程数为："+arrayBlockingQueue.size());
				}
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				long b = System.currentTimeMillis()-a;
				System.out.println("线程提交时间间隔"+b);
			}
		}


//		for (int i = 0; i < t; i++) {
//			executorService.execute(() -> x.isSyncA());
//
//			executorService.execute(() -> x.isSyncB());
//		}



		executorService.shutdown();

		/**
		// Thread t11 = new Thread(
		//				 () -> x.isSyncA(), "t11");
		//
		// 新建t12, t12会调用 x.isSyncB()
		// Thread t12 = new Thread(() -> x.isSyncB(), "t12");
		//
		//
		// t11.start();  // 启动t11
		// t12.start();  // 启动t12
		*/
	}
	
	public static void main(String[] args) {
		LockDemo1Something demo1 = new LockDemo1Something();
		demo1.Test1();
	}
	/**
	// 结果
	
	//	t11  : isSynA
	//	t11  : isSynA
	//	t11  : isSynA
	//	t11  : isSynA
	//	t11  : isSynA
	//	t12  : isSynB
	//	t12  : isSynB
	//	t12  : isSynB
	//	t12  : isSynB
	//	t12  : isSynB

	// 因为在test1中 isSyncA和isSyncB都是访问同一对象的同步锁。

	 同样 从线程池创建线程 运行后的结果看出也是一致的
	 pool-1-thread1  : isSynA
	 pool-1-thread1  : isSynA
	 pool-1-thread1  : isSynA
	 pool-1-thread1  : isSynA
	 pool-1-thread1  : isSynA
	 pool-1-thread2  : isSynB
	 pool-1-thread2  : isSynB
	 pool-1-thread2  : isSynB
	 pool-1-thread2  : isSynB
	 pool-1-thread2  : isSynB
	 */
	static class MyTask implements Runnable {
		private int taskNum;

		public MyTask(int num) {
			this.taskNum = num;
		}
		@Override
		public void run() {
			System.out.println("正在执行task "+taskNum);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("task "+taskNum+"执行完毕");
		}
	}


}



