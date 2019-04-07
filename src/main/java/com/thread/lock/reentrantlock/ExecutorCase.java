package com.thread.lock.reentrantlock;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;


/**
 * ClassName: ExecutorCase
 * Description: 线程池实现原理
 * Date:     2019/3/18 20:05
 * History:
 * <version> 1.0
 * @author lin
 */
public class ExecutorCase {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5,20,200,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
          int count = 100;
          for (int i =0; i<count;i++){
              executorService.execute(new Task());
          }
    }


    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
