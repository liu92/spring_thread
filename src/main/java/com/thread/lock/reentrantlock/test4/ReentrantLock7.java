package com.thread.lock.reentrantlock.test4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的使用。
 * 使用ReentrantLock 可以完成synchronized同样的功能
 * 需要注意的是，必须必须必须 要进行手动释放锁（重要的事情说三部）
 * 在使用synchronized 锁定的话如果遇到了异常，jvm会自动释放锁，
 *  但是lock必须手动释放锁，因此经常在finally进行锁的释放。
 *
 * 使用ReentrantLock可以进行“尝试锁定”tryLock, 这样无法锁定，或者在指定时间内无法锁定，
 * 线程可以决定是否继续等待
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法， 可以对线程interrupt方法做出响应
 * 在一个线程中等待锁的释放过程中，可以被打断
 *
 * ReentrantLock还可以指定为公平锁
 *
 * @ClassName: ReentrantLock4
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 10:11
 * History:
 * @<version> 1.0
 */
public class ReentrantLock7 extends  Thread{
    /**
     *  参数为true表示是一个公平锁，请对比输出结果
     */
    private static ReentrantLock REENTRANT_LOCK = new ReentrantLock(true);

    /**
     */
    @Override
    public void run() {
        int count = 100;
        for (int i=0; i<count; i++){
            REENTRANT_LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() +"获得锁");
            }finally {
               REENTRANT_LOCK.unlock();
            }

        }
    }

    public static void main(String[] args) {
       ReentrantLock7 rl = new ReentrantLock7();
       Thread t1 = new Thread(rl);
       Thread t2 = new Thread(rl);
       t1.start();
       t2.start();
    }


}
