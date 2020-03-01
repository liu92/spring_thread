package com.thread.lock.countdemo2;

import java.util.concurrent.TimeUnit;

/**
 *  第四条
 *  一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍可以得到该对象的锁
 *  也就是说synchronized获得的锁是可以重入的。
 *  这里是继承中有可能发生的情形，子类调用父类的同步方法. 这种方式也就是进行锁的重入
 * @ClassName: Tet1
 * @Description:
 * @Author: lin
 * @Date: 2019/11/22 16:39
 * History:
 * @<version> 1.0
 */
public class Tet1 {
    synchronized void  method1(){
        System.out.println("m1 start");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Tet11().method1();
    }
}

class  Tet11 extends Tet1{
    @Override
    synchronized void method1() {
        System.out.println("child method1 start");
        super.method1();
        System.out.println("child method1 end");
    }
}