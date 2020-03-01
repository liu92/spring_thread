package com.thread.ticket;

import com.thread.lock.DefaultThreadFactory;

import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 买票问题
 * 如何保证 票不超卖，不重复卖
 * 下面的代码 存在的问题就是 ：
 * 1、在多线程情况下 可能这个集合中的一张票已经卖出去，
 *  但是其他线程进来后会再次去卖这一张票，这样就造成了票的的重复卖
 * 2、还有就是当容器中最后一张已经卖出去的时候，其他线程再来进行remove操作，这时就会造成数组越界，并且remove不是一个原子操作
 * 3、方法全部都是不同步方法，所以会出现各种问题
 *
 * 第一种解决方法:
 * 1、使用同步容器Vector, 因为是同步容器 并且所有方法都加了锁。
 * @ClassName: TicketSeller1
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 17:32
 * History:
 * @<version> 1.0
 */
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();
    static {
        int count =10000;
        for (int i=0; i<count; i++){
            tickets.add("票编号: " + i);
        }
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        int c = 10;
       for (int i=0; i< c; i++){
           MyTaskTicket tk = new MyTaskTicket();
           executorService.execute(tk);
       }
    }


    static class MyTaskTicket implements Runnable {
        @Override
        public void run() {
            // Vector 虽然 下面的方法是原子性的，但是判断和操作分离了。
            // 也就是说 在这个size判断和 remove这两个操作中间  有可能被打断，
            //  那么在被打断的情况下，还是会出现问题
            // 这种很有可以有其他逻辑和判断 ,比如下面的情况
            // 例如 线程A在进行了判断之后，还没有进行remove操作时，线程B 已经将这个元素remove了，
            // 那么当线程A执行remove的时候 就会造成错误，队列中已经没有这个数据。。
            // size+remove 这样不是原子操作
            while (tickets.size() > 0 ){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("销售了---"+ tickets.remove(0));
            }
        }
    }

}
