package com.thread.lock.countdemo2;

import java.util.concurrent.TimeUnit;

/**
 *  // 第四条
 * 	  一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍可以得到该对象的锁
 * 	   也就是说synchronized获得的锁是可以重入的。
 * @ClassName: Tet
 * @Description:
 * @Author: lin
 * @Date: 2019/11/22 16:37
 * History:
 * @<version> 1.0
 */
public class Tet {
    synchronized void  method1(){
        System.out.println("m1 start");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
    }

    synchronized  void  method2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
}
