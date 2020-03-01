package com.thread.threadpool;

import com.thread.lock.DefaultThreadFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * 并行计算
 * @ClassName: T4ParallelComputing
 * @Description:
 * @Author: lin
 * @Date: 2019/11/24 23:41
 * History:
 * @<version> 1.0
 */
public class T4ParallelComputing {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> results = null;
        long end = System.currentTimeMillis();
        System.out.println(end -start);
        final int cpuCoreNum = 4;


        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(200),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(1300001, 170000);
        MyTask t4 = new MyTask(1700001, 200000);

      //  FutureTask<List<Integer>> f1 = executorService.submit(t1);
    }

    static  class  MyTask implements Callable<List<Integer>>{
         int startPos, endPos;
        MyTask(int s, int e){
            this.startPos = s;
            this.endPos = e;
        }
        /**
         */
        @Override
        public List<Integer> call() throws Exception {
            return null;
        }
    }
}
