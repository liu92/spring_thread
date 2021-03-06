package com.thread.lock.reentrantlock.test2;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * 消费者
 * <p>Title:Customer</p>
 * @author liuwanlin
 * @date 2018年2月12日下午5:50:33
 */
public class Customer2 {
	private Depot2 depot2;

     Customer2(Depot2 depot2) {
        this.depot2 = depot2;
    }

    /**
     *  消费产品：新建一个线程从仓库中消费产品。
     * @param val 数量
     */
    public void consume(final int val) {

        ExecutorService executorService = new ThreadPoolExecutor(5,
                50, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(()->depot2.consume(val));
        executorService.shutdown();

        /**
         *
         *new Thread() {
           @Override
           public void run() {
           depot2.consume(val);
         }
        }.start();
         */
    }
}
