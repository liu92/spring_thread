package com.thread.produceandconsumer;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生产者
 * <p>Title:Producer</p>
 * @author liuwanlin
 * @date 2018年1月12日下午3:53:58
 */
public class Producer {

	private Depot depot;
	
	 Producer(Depot depot){
		this.depot=depot;
	}
	
	 /** 消费产品：新建一个线程向仓库中生产产品。*/
	public void produce(final int val){

		ExecutorService executorService = new ThreadPoolExecutor(5,
				50, 200,
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
				new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		executorService.execute(()->depot.produce(val));
		executorService.shutdown();

//		new Thread(){
//			@Override
//			public void run(){
//				depot.produce(val);
//			}
//		}.start();
	}
}
