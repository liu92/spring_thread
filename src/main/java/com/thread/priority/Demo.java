package com.thread.priority;

/**
 * 
 * <p>Title:Demo</p>
 * @author liuwanlin
 * @date 2018年1月5日下午5:45:35
 */
public class Demo {
    public static void main(String[] args) {
    	System.out.println(Thread.currentThread().getName()
                +"("+Thread.currentThread().getPriority()+ ")");
    	
    	
    	  Thread t1=new MyThread("t1");    // 新建t1
          Thread t2=new MyThread("t2");    // 新建t2
          t1.setPriority(1);               // 设置t1的优先级为1
          t2.setPriority(10);              // 设置t2的优先级为10
          t1.start();                      // 启动t1
          t2.start();                      // 启动t2
	}
    // 从打印来看
    /**
     * 1：主线程的线程优先级是5
     * 2：t1的优先级被设为 1，而t2的优先级被设为 10，cpu在执行t1和t2的时候，根据时间片轮循调度，所以能够并发执行。
     */
}
