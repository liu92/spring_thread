package com.thread.produceandconsumer;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 * <p>Title:Customer</p>
 * @author liuwanlin
 * @date 2018年1月12日下午3:49:39
 */
public class Customer {

	private Depot depot;
	
	
	 Customer(Depot depot){
		this.depot=depot;
	}
	
	
	/** 这里使用final是因为 ，下面是 一个匿名内部类*/
	public void consume(final int val){
		ExecutorService executorService = new ThreadPoolExecutor(5,
				50, 200,
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		executorService.execute(()->depot.consume(val));
		executorService.shutdown();

//		new Thread(){
//			@Override
//			public void run(){
//				depot.consum(val);
//			}
//		}.start();
	}
	
	
	
}
