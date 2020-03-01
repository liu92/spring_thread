package com.thread.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue队列： 这个可以用来做定时执行任务
 * @ClassName: T7DelayQueue
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:59
 * History:
 * @<version> 1.0
 */
public class T7DelayQueue {
    /**
     *DelayQueue 无界队列，这个队列中每一个元素 我们可以理解为一个个任务
     * 这个元素什么时候可以被消费者往外拿呢？只有等一段时间之后 才可以，
     * 每一个元素自己记载着还有多少时间 被消费者拿走消费。
     * 这个队列默认是排好顺序的，等待时间最长的排在最前面，等待时间最长的最先往外拿
     * 可以通过compareTo来自己定义
     *
     * 这个可以用来做定时执行任务
     */
    static BlockingQueue<MyTaskDelay> str = new DelayQueue<>();
    static Random r = new Random();
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        //第一个任务表示的是，现在开始1s后执行
        MyTaskDelay t1 = new MyTaskDelay(now + 1000);
        //第二个任务表示的是，现在开始2s后执行
        MyTaskDelay t2 = new MyTaskDelay(now + 2000);
        //第三个任务表示的是，现在开始1.5s后执行
        MyTaskDelay t3 = new MyTaskDelay(now + 1500);
        MyTaskDelay t4 = new MyTaskDelay(now + 2500);
        MyTaskDelay t5 = new MyTaskDelay(now + 500);
        str.put(t1);
        str.put(t2);
        str.put(t3);
        str.put(t4);
        str.put(t5);

        System.out.println(str);
        int tt = 5;
        for (int i=0;i<tt;i++){
            //然后把任务拿出来
            System.out.println(str.take());
        }
    }

    /**
     * 实现Delayed接口
     */
    static  class  MyTaskDelay implements Delayed{
        long runningTime;
        MyTaskDelay(long rt){
            this.runningTime  = rt;
        }
     /**

      */
     @Override
     public int compareTo(Delayed o) {
         if(this.getDelay(TimeUnit.MILLISECONDS)< o.getDelay(TimeUnit.MILLISECONDS)){
             return  -1;
         }else  if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
             return  1;
         }
         return 0;
     }
     /**

      */
     @Override
     public long getDelay(TimeUnit unit) {
         return unit.convert(runningTime - System.currentTimeMillis() ,TimeUnit.MILLISECONDS);
     }


 }








}
