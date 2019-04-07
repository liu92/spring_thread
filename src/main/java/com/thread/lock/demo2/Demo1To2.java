package com.thread.lock.demo2;

/**
 * 将Demo1_1 进行修改 ，改成Demo1_2 看结果
 * <p>Title:Demo1To2</p>
 * @author liuwanlin
 * @date 2017年8月15日下午2:18:59
 */
public class Demo1To2 {


    /**
     * 	代码说明：比较Demo1To2 和 Demo1To1，我们发现，比较Demo1To2中的MyThread类是直接继承于Thread，而且t1和t2都是MyThread子线程。
     	幸运的是，在“比较Demo1To2()方法”也调用了synchronized(this)，正如“Demo1To1的run()方法”也调用了synchronized(this)一样！
     * @param args
     */
	public static void main(String[] args) {
        // 新建“线程t1”
      Thread t1 = new MyThread("t1");
        // 新建“线程t2”
      Thread t2 = new MyThread("t2");
      t1.start();                          // 启动“线程t1”
      t2.start();                          // 启动“线程t2” 
	}
	//  结果
	//	t2 loop0
	//	t1 loop0
	//	t2 loop1
	//	t1 loop1
	//	t2 loop2
	//	t1 loop2
	//	t2 loop3
	//	t1 loop3
	//	t2 loop4
	//	t1 loop4
    
    // 从结果可以看出 synchronized (this) "表示当前类的对象" ，即synchronized(this)所在的类对应的当前对象。它的作用是获取“当前对象的同步锁”。
	// 在 Demo1To2 中，synchronized (this) 中this代表的是MyThread	,而t1和t2是两个不同的MyThread,因此t1和t2在执行synchronized (this),
	// 获取的是不同的同步锁，对应demo1_1而言，synchronized(this)中的this代表的是MyRunable对象；
	// t1和t2共同一个MyRunable对象，因此，一个线程获取了对象的同步锁，会造成另外一个线程等待。
}
