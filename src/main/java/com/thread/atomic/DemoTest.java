package com.thread.atomic;

/**
 * 测试 原子性，　虽然递增操作++i是一种紧凑的语法，使其看上去只是一个操作，但这个操作并非原子的，因而它并不会作为一个不可分割的操作来执行。实际上，它包含了三个独立的操作：
 * 读取count的值，将值加1，然后将计算结果写入count。这是一个“读取 - 修改 - 写入”的操作序列，并且其结果状态依赖于之前的状态。
 * @ClassName: DemoTest
 * @Description:
 * @Author: lin
 * @Date: 2019/9/30 22:06
 * History:
 * @<version> 1.0
 */
public class DemoTest {

    public static void main(String[] args) {
        int  count = 0;
        count++;
        System.out.println(count);
    }
}
