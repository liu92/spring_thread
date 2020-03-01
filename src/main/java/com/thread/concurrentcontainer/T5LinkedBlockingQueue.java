package com.thread.concurrentcontainer;

import com.thread.lock.DefaultThreadFactory;

import java.util.Random;
import java.util.concurrent.*;

/**
 * LinkedBlockingQueue队列
 * @ClassName: T5LinkedBlockingQueue
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:59
 * History:
 * @<version> 1.0
 */
public class T5LinkedBlockingQueue {
    static  BlockingQueue<String> str = new LinkedBlockingQueue<>();
    static Random r = new Random();
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5,
                50, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        ThreadLinkedQueueA tha = new ThreadLinkedQueueA();
        // 创建一个生产者线程，这个生产者线程完队列中加了一百个数据。
        // 每加一个数据然后随机睡眠一段时间
        executorService.execute(tha);

        int st = 5;
        // 使用5个消费者线程，在不同的进行消费
        for (int i=0; i< st; i++){
            ThreadLinkedQueueB thb = new ThreadLinkedQueueB("c"+i);
            executorService.execute(thb);
        }
    }



    static  class  ThreadLinkedQueueA implements Runnable{

        @Override
        public void run() {
            int count = 100;
            for (int i=0; i< count; i++){
                try {
                    //阻塞式容器，如果容器满了，就会等待
                    str.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    static  class  ThreadLinkedQueueB implements Runnable{
        String name;
        ThreadLinkedQueueB(String name){
            this.name = name;
        }
        @Override
        public void run() {
            for(;;){
                try {
                    // 如果空了，就会等待
                    System.out.println(name +"  take -" +str.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }






}
