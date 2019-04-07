package com.thread.lock.reentrantlock.test3;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生产者
 * <p>Title:Producer3</p>
 * @author liuwanlin
 * @date 2018年2月23日上午11:29:13
 */
public class Producer3 {
	private Depot3 depot3;
	
	public Producer3(Depot3 depot3){
		this.depot3=depot3;
	}

	// 消费产品：新建一个线程向仓库中生产产品。
	
	public void produce(final int val){


		ExecutorService executorService = new ThreadPoolExecutor(5,
				50, 200,
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


		executorService.execute(()->depot3.produce(val));

		executorService.shutdown();
//		new Thread(){
//			@Override
//			public void run(){
//				depot3.produce(val);
//			}
//		}.start();
	}
}
