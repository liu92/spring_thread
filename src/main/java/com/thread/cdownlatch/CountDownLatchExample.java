package com.thread.cdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchExample
 * @Description:
 * @Author: lin
 * @Date: 2019/9/22 10:30
 * History:
 * @<version> 1.0
 */
public class CountDownLatchExample {
    private static final CountDownLatch LATCH = new CountDownLatch(4);
    private static int data;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                data = i;
                LATCH.countDown();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
             }
            }
        };
        thread.start();
        LATCH.await();
        System.out.println("It's done. data=" + data);

//        ExecutorService executorService = new ThreadPoolExecutor(5,
//                50, 200,
//                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
//                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
//        executorService.execute(new ThreadA());
//        System.out.println("It's done. data=" + data);
//        LATCH.await();
    }

//
//    static  class  ThreadA implements Runnable{
//        @Override
//        public void run() {
//            for (int i = 0; i < 10; i++) {
//                data = i;
//                LATCH.countDown();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
