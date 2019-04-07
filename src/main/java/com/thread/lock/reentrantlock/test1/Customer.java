package com.thread.lock.reentrantlock.test1;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 * <p>Title:Customer</p>
 * @author liuwanlin
 * @date 2018年2月12日下午5:50:33
 */
public class Customer {
	private Depot depot;

     Customer(Depot depot) {
        this.depot = depot;
    }

    /**消费产品：新建一个线程从仓库中消费产品。*/
    public void consume(final int val) {
        ExecutorService executorService = new ThreadPoolExecutor(5,
                50, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(()->depot.consume(val));

        executorService.shutdown();
    /**
    * 以前写法
    // new Thread() {
    // @Override
    // public void run() {
    //       depot.consume(val);
    //   }
    //  }.start();
    */
    }
}
