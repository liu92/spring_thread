package com.thread.ticket;

import com.thread.lock.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
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
 * @ClassName: TicketSeller1
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 17:32
 * History:
 * @<version> 1.0
 */
public class TicketSeller1 {
    static List<String> tickets = new ArrayList<>();
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
            while (tickets.size() > 0 ){
                System.out.println("销售了---"+ tickets.remove(0));
            }
        }
    }

}
