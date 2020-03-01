package com.thread.local;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * @ClassName: ThreadLocalDemo1
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 16:33
 * History:
 * @<version> 1.0
 */
public class ThreadLocalDemo1 {
    volatile  static  Person p = new Person();

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
            System.out.println(p.name);
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
            p.name ="hh";
        }
    }

}

class  Person {
    String name ="lin";
}
