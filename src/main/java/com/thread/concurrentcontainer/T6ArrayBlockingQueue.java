package com.thread.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue队列
 * @ClassName: T6ArrayBlockingQueue
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:59
 * History:
 * @<version> 1.0
 */
public class T6ArrayBlockingQueue {
    static BlockingQueue<String> str = new ArrayBlockingQueue<>(10);
    static Random r = new Random();
    public static void main(String[] args) {
        // 这个要设置队列初始大小, 有界队列 ,也就是这个容器中装的个数是固定的
        int tc = 10;
        for (int i=0; i<tc; i++){
            try {
                str.put("a" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 满了就会等待，程序阻塞
        //str.put("aa");
        // 这个添加元素 满了之后 会报错
        //str.add("aa");
        // 这个添加元素 满了之后 不会报错，而是通过返回值判断是否加入成功
        str.offer("aa");
        // 按时间段加元素，如果一段时间没有加入进去，那么就不往里面加入了
        // str.offer("aa", 1 , TimeUnit.SECONDS);

        System.out.println(str);
    }










}
