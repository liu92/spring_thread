package com.thread.concurrentcontainer;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 同步容器-------之ConcurrentMap
 *
 * http://blog.csdn.net/sunxianghuang/article/details/52221913

 * 阅读ConcurrentSkipListMap
 *
 * @ClassName: T1_ConcurrentMap
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 18:19
 * History:
 * @<version> 1.0
 */
public class T1ConcurrentMap {
    public static void main(String[] args) {
        //Map<String, String> map = new ConcurrentHashMap<>();
        // 高并发并且排序 ，插入效率会低一些，因为他排序了
        //Map<String, String> map = new ConcurrentSkipListMap<>();

        /**
         * 这里有一个map容器，然后起了 100个线程，这一百个线程，每个都向里面装10000个数据
         * 也就是总共装了一百万数据。之后每个线程执行完了之后，latch 是100 ，就往下减个1，
         * 主线程等着，其他线程执行完毕 最后计算一个时间, 然后看看效率问题
         */
        Map<String, String> map = new Hashtable<>();
        //Map<String, String> map = new HashMap<>();
        // TreeMap
        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for(int i=0; i< threads.length; i++) {
            threads[i] = new Thread(()->{
                int cou = 10000;
                for (int j=0; j<cou; j++){
                    map.put("a" + r.nextInt(100000), "a"+r.nextInt(100000));
                    latch.countDown();
                }
            });
        }
        Arrays.asList(threads).forEach(t->t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }







}

