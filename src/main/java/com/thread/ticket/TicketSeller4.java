package com.thread.ticket;

import com.thread.lock.DefaultThreadFactory;

import java.util.Queue;
import java.util.concurrent.*;

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
 * 2、使用synchronized 关键字，在没卖出一张票的时候加一把锁, 锁定tickets
 * 3、使用队列来处理
 * @ClassName: TicketSeller1
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 17:32
 * History:
 * @<version> 1.0
 */
public class TicketSeller4 {
    /**
     * ConcurrentLinkedQueue 链表实现的队列，它是一个并发队列
     */
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
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
            while (true) {
                // 调用poll(), 从队列中拿出一个元素，从头上拿出，这个poll是同步的
                // 如果在队列中没有拿到元素，返回就是null。 也就是队列为空
                // 这里我们没有对 队列进行修改操作。 其他的方式都对 队列进行修改操作
                // 并且这里如果一个线程A从队列中取出元素 如果将队列中的数据取完了，
                // 而那么其他线程B 再次去拿一遍。这里是while(true) 循环，所以不会出现问题
                // 并且不用加锁了，效率会高很多
                String s = tickets.poll();
                if (s == null) {
                    break;
                }else {
                    System.out.println("销售了---" + s);
                }
            }
        }
    }

}
