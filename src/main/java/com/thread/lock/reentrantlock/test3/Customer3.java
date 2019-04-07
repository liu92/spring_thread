package com.thread.lock.reentrantlock.test3;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Customer3 {
  private Depot3 depot3;
  
  public Customer3(Depot3 depot3){
	  this.depot3 = depot3;
  }

	/**
	 * 消费产品：新建一个线程从仓库中消费产品。
	 * @param val
	 */

	public void consume(final int val){

		ExecutorService executorService = new ThreadPoolExecutor(5,
				50, 200,
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		executorService.execute(()->depot3.consume(val));
		executorService.shutdown();
//	  new Thread(){
//		  @Override
//		  public void run(){
//			 depot3.consume(val);
//		  }
//	  }.start();
  }
}
