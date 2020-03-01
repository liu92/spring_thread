package com.thread.concurrentcontainer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 多线程环境下， 写时效率低，读时效率高
 * 适合写少读多的环境
 * @ClassName: T2CopyWriteList
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:41
 * History:
 * @<version> 1.0
 */
public class T2CopyWriteList {

    public static void main(String[] args) {
        List<String> lists =
                //new ArrayList<>(); 多线程情况下会出现并发问题。
                //new Vector<>();
                // 添加数据特别慢，每一次往里加数据时都要复制一份
                // 不过在读取的时候，会从新的地方来读，
                // 并且对于从里往外读数据的线程不用加锁了
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        for(int i=0; i< threads.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    int t = 1000;
                    for (int j=0; j<t; j++){
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            };
            threads[i]  = new Thread(task);
        }
        runAndComputeTime(threads);
        System.out.println(lists.size());
    }

    /**
     *
     * @param threads
     */
    private static  void  runAndComputeTime(Thread[] threads){
        long s1 = System.currentTimeMillis();
        Arrays.asList(threads).forEach(t->t.start());
        Arrays.asList(threads).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }













}
