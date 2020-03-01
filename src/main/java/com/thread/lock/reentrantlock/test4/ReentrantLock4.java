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
 * @ClassName: ReentrantLock4
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 10:11
 * History:
 * @<version> 1.0
 */
public class ReentrantLock4 {
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

    void  m2(){
        lock.lock();
        System.out.println("m2.........");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock4 rl = new ReentrantLock4();

        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new MyTaskReentrantLock1("t1",rl));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.execute(new MyTaskReentrantLock2("t2",rl));
        executorService.shutdown();
    }


    static class MyTaskReentrantLock1 implements Runnable {
        String name;
        ReentrantLock4 rl;
        MyTaskReentrantLock1(String name, ReentrantLock4 rl) {
            this.name = name;
            this.rl = rl;
        }
        @Override
        public void run() {
            rl.m1();
            System.out.println(name );
        }
    }

    static class MyTaskReentrantLock2 implements Runnable {
        String name;
        ReentrantLock4 rl;
        MyTaskReentrantLock2(String name, ReentrantLock4 rl) {
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
