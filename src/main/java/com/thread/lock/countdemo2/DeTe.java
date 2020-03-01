package com.thread.lock.countdemo2;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况下锁会被释放。synchronized 遇到异常锁会被释放。
 * 所以在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 * 比如一个线程在持有synchronized 进行锁定的情况下，如果这个线程在修改数据的情况下，刚刚修改到一半的数据，
 *  这个时候出现异常，那么其他的线程在处理的时候 这时读取到的数据就是 修改到一半的数据，也就是不正确的数据，
 * @ClassName: DeTe
 * @Description:
 * @Author: lin
 * @Date: 2019/11/22 16:49
 * History:
 * @<version> 1.0
 */
public class DeTe {
    int count = 0;
     synchronized  void  met1(){
        System.out.println(Thread.currentThread().getName()+ " m1 start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count==5){
                //此处抛出异常 ,锁将被释放，想要不被释放，可以在这里进行catch,然后让循环继续执行。
                // 这里抛异常之后，如果不释放锁 t2就永远启动不起来。 也就是说在出现异常后才会释放锁才会执行t2
                int i = 1/0 ;
            }
        }
    }

    public static void main(String[] args) {
        DeTe te = new DeTe();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(new MyTask("t1",te));
        try {
            // 睡一秒
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.execute(new MyTask("t2",te));

        executorService.shutdown();
    }


    static class MyTask implements Runnable {
         String name;
        private  DeTe deTe;
        MyTask(String name, DeTe deTe) {
            this.name = name;
            this.deTe = deTe;
        }
        @Override
        public void run() {
            deTe.met1();
        }
    }
}
