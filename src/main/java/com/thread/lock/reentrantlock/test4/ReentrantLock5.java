package com.thread.lock.reentrantlock.test4;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的使用。
 * 使用ReentrantLock 可以完成synchronized同样的功能
 * 需要注意的是，必须必须必须 要进行手动释放锁（重要的事情说三部）
 * 在使用synchronized 锁定的话如果遇到了异常，jvm会自动释放锁，
 *  但是lock必须手动释放锁，因此经常在finally进行锁的释放。
 *
 * 使用ReentrantLock可以进行“尝试锁定”tryLock, 这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 *
 * @ClassName: ReentrantLock4
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 10:11
 * History:
 * @<version> 1.0
 */
public class ReentrantLock5 {
    Lock lock = new ReentrantLock();

    void m1(){
        lock.lock();
        try {
            int count =10;
            for (int i=0; i<count; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 使用ReentrantLock可以进行“尝试锁定”tryLock,
     * 不管锁定与否，方法都将继续执行
     * 也可以指定tryLock的时间，用于tryLock(time)抛出异常。
     * 所以要注意unlock的处理，必须放在finally中
     */
    void  m2(){
        /**
        boolean locked = lock.tryLock();
        System.out.println("m2....."+locked);
        if(locked){
            lock.unlock();
        }*/

        boolean locked = false;
        try {
            // 指定时间长度和时间单位， 也就是在这里尝试等待锁5秒钟，
            // 如果5秒钟还没有锁定 ，那么该干嘛 就去干嘛
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2........."+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(locked){
                lock.unlock();
            }
        }



    }

    public static void main(String[] args) {
        ReentrantLock5 rl = new ReentrantLock5();

        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new MyTaskReentrantLock3("t1",rl));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.execute(new MyTaskReentrantLock4("t2",rl));
        executorService.shutdown();
    }


    static class MyTaskReentrantLock3 implements Runnable {
        String name;
        ReentrantLock5 rl;
        MyTaskReentrantLock3(String name, ReentrantLock5 rl) {
            this.name = name;
            this.rl = rl;
        }
        @Override
        public void run() {
            rl.m1();
            System.out.println(name );
        }
    }

    static class MyTaskReentrantLock4 implements Runnable {
        String name;
        ReentrantLock5 rl;
        MyTaskReentrantLock4(String name, ReentrantLock5 rl) {
            this.name = name;
            this.rl = rl;
        }
        @Override
        public void run() {
            rl.m2();
            System.out.println(name);
        }
    }


}
