package com.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  创建线程池

 * @ClassName:      CustomizePoolExeutor
 * @Description:    创建线程池
 * @Author:         作者姓名 liuwl
 * @CreateDate:     2018/6/27 0027 17:42
 */

public class CustomizePoolExeutor {

    public static void main(String[] args) {
        CustomizeThreadFactory customizeThreadFactory = new CustomizeThreadFactory();

        CustomizeRejectedExecutionHandler executionHandler = new CustomizeRejectedExecutionHandler();

        // 创建 有界工作队列
        BlockingQueue<Runnable> q = new ArrayBlockingQueue<Runnable>(10);

        ThreadPoolExecutor threadPoolExecutor =new ThreadPoolExecutor(4,8,10, TimeUnit.SECONDS,q,
                customizeThreadFactory,executionHandler);

        for (int i = 0; i <10 ; i++) {

        }

        // 需要关闭线程池
        threadPoolExecutor.shutdown();
    }
}
