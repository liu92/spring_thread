package com.thread;

/**
 * 使用hsdis 测试反汇编
 * @ClassName: Bar
 * @Description:
 * @Author: lin
 * @Date: 2019/10/17 18:02
 * History:
 * @<version> 1.0
 */
public class Bar {
    int a = 1;
    static int b = 2;

    public int sum(int c) {
        return a + b + c;

    }

    public static void main(String[] args) {
        new Bar().sum(3);
    }
}
