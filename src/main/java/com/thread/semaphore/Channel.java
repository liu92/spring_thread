package com.thread.semaphore;

/**
 * 对传输通道的抽象
 * @ClassName: Channel
 * @Description:
 * @Author: lin
 * @Date: 2019/9/22 12:02
 * @History:
 * @<version> 1.0
 */
public interface Channel<Product> {

    /**
     * 往传输通道中存入一个产品
     * @param product
     * @throws InterruptedException
     */
    void  put(Product product)throws  InterruptedException;

    /**
     * 从传输通道中取出一个产品
     * @return
     * @throws InterruptedException
     */
    Product take()throws  InterruptedException;

}
