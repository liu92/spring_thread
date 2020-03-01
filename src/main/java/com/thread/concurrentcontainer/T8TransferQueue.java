package com.thread.concurrentcontainer;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * TransferQueue队列
 * 它提供了一个转移的方法
 * 一个队列，先启动消费者，然后生产者生成数据的时候，它首先不是往消费者里加，
 * 而先去查找有没有消费者，如果有消费者 生成的数据就不往队列中放了，而是直接给消费者消费
 * 如果生成者先起启来，如没有消费者 那么就会阻塞
 * @ClassName: T7DelayQueue
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:59
 * History:
 * @<version> 1.0
 */
public class T8TransferQueue {

     static   LinkedTransferQueue<String> str = new LinkedTransferQueue<String>();
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(5,
                50, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        ThreadTransferQueue transferQueue = new ThreadTransferQueue();
        //消费者先启
        executorService.execute(transferQueue);
        // 如果生产者启动，那么这里会阻塞
        str.transfer("aaa");
        //消费者后启动
        //executorService.execute(transferQueue);
    }



    static  class  ThreadTransferQueue implements Runnable{
        @Override
        public void run() {
            try {
                str.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }






}
