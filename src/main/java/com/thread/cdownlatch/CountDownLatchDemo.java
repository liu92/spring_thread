/**
 * Copyright (C), 2018-2019, XXX有限公司
 * FileName: CountDownLatchDemo
 * Author:   lin
 * Date:     2019/1/30 23:36
 * Description: CountDownLatch用法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.thread.cdownlatch;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈CountDownLatch用法〉
 *
 * @author lin
 * @create 2019/1/30
 * @since 1.0.0
 */
public class CountDownLatchDemo {
   static final CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) {


        ExecutorService executorService = new ThreadPoolExecutor(5,
                50, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new ThreadA());
        executorService.execute(new ThreadB());
        executorService.shutdown();
        try {
            System.out.println("等待2个子线程执行完毕...");
            countDownLatch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static  class  ThreadA implements Runnable{

        @Override
        public void run() {

            try {
                System.out.println("ThreadA子线程"+Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
                System.out.println("ThreadA子线程"+Thread.currentThread().getName()+"执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }

        }
    }

    static  class  ThreadB implements Runnable{
        @Override
        public void run() {

            try {
                System.out.println("ThreadB子线程"+Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
                System.out.println("ThreadB子线程"+Thread.currentThread().getName()+"执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        }
    }

//    ThreadA子线程pool-1-thread1正在执行
//    ThreadB子线程pool-1-thread2正在执行
//    等待2个子线程执行完毕...
//    ThreadA子线程pool-1-thread1执行完毕
//    ThreadB子线程pool-1-thread2执行完毕
//    2个子线程已经执行完毕
//    继续执行主线程
}
