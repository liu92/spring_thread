package com.thread.concurrentcontainer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue队列
 * @ClassName: T4ConurrentQueue
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:59
 * History:
 * @<version> 1.0
 */
public class T4ConcurrentQueue {
    public static void main(String[] args) {
        // 无界队列 ，单向队列
        Queue<String> str = new ConcurrentLinkedQueue<>();
        int tc = 10;
        for (int i=0; i<tc; i++){
            // 添加元素，通过返回值来判断是否加入成功
            str.offer("a" + i);
        }
        System.out.println("队列中的元素:" + str);
        System.out.println("队列中的元素个数:" +str.size());
        // 从队列中取出第一个元素来 并且删除队列中的元素
        System.out.println("从队列中取出第一个元素:"+str.poll());
        System.out.println("从队列中取出第一个元素后 队列中的元素:" + str);
        System.out.println("从队列中取出一个元素后队列中元素个数:"+ str.size());
        // 从队列中取出一个元素来使用但是 不删除队列中的元素
        System.out.println("从队列中取出第一个元素:"+ str.peek());
        System.out.println(str.size());

        // 双端队列 Deque,就是双向链表了
    }





}
