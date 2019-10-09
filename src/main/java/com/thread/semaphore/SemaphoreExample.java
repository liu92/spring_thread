package com.thread.semaphore;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 *  信号量测试
 *  流量控制与信号量
 * @ClassName: SemaphoreExample
 * @Description:
 * @Author: lin
 * @Date: 2019/9/22 12:00
 * History:
 * @<version> 1.0
 */
public class SemaphoreExample<Product> implements Channel<Product> {

    private final BlockingQueue<Product> queue;
    private final Semaphore semaphore;


    /**
     *
     * @param queue
     *       阻塞队列，通常是一个无界阻塞队列
     * @param flowLimit
     *        流量限制数
     */
    public SemaphoreExample(BlockingQueue<Product> queue, int flowLimit){
        this(queue, flowLimit, false);
    }

    public SemaphoreExample(BlockingQueue<Product> queue, int flowLimit, boolean isFair){
        this.queue = queue;
        this.semaphore = new Semaphore(flowLimit, isFair);
    }

    /**
     * 从传输通道中取出一个产品
     *
     * @return
     * @throws InterruptedException
     */
    @Override
    public Product take() throws InterruptedException {
        return queue.take();
    }

    /**
     * 往传输通道中存入一个产品
     *
     * @param product
     * @throws InterruptedException
     */
    @Override
    public void put(Product product) throws InterruptedException {
        // 申请配额
       semaphore.acquire();
       try {
           // 访问虚拟资源
           queue.put(product);
       }finally {
           // 返还一个配额
           semaphore.release();
       }
    }


}
