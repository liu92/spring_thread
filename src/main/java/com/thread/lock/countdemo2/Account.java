package com.thread.lock.countdemo2;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题（dirtyRead）， 解决办法CopyOnWrite
 * @ClassName: Account
 * @Description:
 * @Author: lin
 * @Date: 2019/11/22 15:21
 * History:
 * @<version> 1.0
 */
public class Account {
    String name;
    double balance;

    public  synchronized  void  set(String name, double balance){
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public  double getBalance(String name){
        return  this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(new MyTask(account));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("lin"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("lin"));
        executorService.shutdown();
    }


    static class MyTask implements Runnable {
        private  Account account;
         MyTask(Account account) {
            this.account = account;
        }
        @Override
        public void run() {
             account.set("lin", 100.0);
        }
    }
}

