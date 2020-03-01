package com.thread.local;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 *  比如在hibernate中session就存在ThreadLocal中，避免使用synchronized 的使用。
 * @ClassName: ThreadLocalDemo1
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 16:33
 * History:
 * @<version> 1.0
 */
public class ThreadLocalDemo2 {
//    volatile  static  Person p = new Person();
    /**
     * 使用ThreadLocal线程局部变量
     */
    static  ThreadLocal<Person2> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new MyTaskThreadLocal1());
        executorService.execute(new MyTaskThreadLocal2());
    }



    static class MyTaskThreadLocal1 implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }
    }

    static class MyTaskThreadLocal2 implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里的变量，自己的线程 自己用，如果其他线程这个变量，那么就需要自己往里添加，
            // 你不能用这个线程放在里面的数据
            tl.set(new Person2());
        }
    }

   static class  Person2 {
        String name ="lin";
    }
}


