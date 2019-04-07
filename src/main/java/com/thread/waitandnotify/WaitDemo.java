package com.thread.waitandnotify;

/**
 * 3对应1、
 * 等待
 * <p>Title:WaitDemo</p>
 * @author liuwanlin
 * @date 2017年8月21日下15:03:27
 */
public class WaitDemo {

	public static void main(String[] args) {
		ThreadWaitDemo t1 =new ThreadWaitDemo("t1");
		ThreadWaitDemo t2 =new ThreadWaitDemo("t2");
		synchronized (t1) {
			try {
				// 启动t1线程
				System.out.println(Thread.currentThread().getName()+" start t1");
				t1.start();
				t2.start();

				System.out.println(Thread.currentThread().getName()+" wait()");
				// 这里是当前线程阻塞
				t1.wait();
				
				System.out.println(Thread.currentThread().getName()+" continue");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//  main start t1
	//	main wait()
	//	t1 call notify
	//	main continue
	
	/** 结果说明    http://wangkuiwu.github.io/2012/08/05/threads-basic/
	//	如下图，说明了“主线程”和“线程t1”的流程。
	//	(01) 注意，图中"主线程" 代表“主线程main”。线程t1" 代表WaitDemo中启动的“线程t1”。 而“锁” 代表“t1这个对象的同步锁”
	//	(02) “主线程”通过 new ThreadA("t1") 新建“线程t1”。随后通过synchronized(t1)获取“t1对象的同步锁”。然后调用t1.start()启动“线程t1”。
	//	(03) “主线程”执行t1.wait() 释放“t1对象的锁”并且进入等待(阻塞)状态”。等待t1对象上的线程通过notify() 或 notifyAll()将其唤醒
	//	(04) “线程t1”运行之后，通过synchronized(this)获取“当前对象的锁”；接着调用notify()唤醒“当前对象上的等待线程”，也就是唤醒“主线程”
	//	(05) “线程t1”运行完毕之后，释放“当前对象的锁。紧接着，“主线程”获取“t1对象的锁”，然后接着运行。
    
	// 问题 ：对于上面的代码？曾经有个朋友问到过： t1.wait()应该是让“线程t1”等待；但是，为什么却是让“主线程main”等待了呢？
	
	// 答：jdk的解释中 文档中关于wait的一段介绍
	
	// 引起“当前线程”等待，直到 “另外一个线程” 调用notify()或notifyAll()唤醒该线程。换句话说，这个方法和wait(0)的效果一样！(补充，对于wait(long
	// millis)方法，当millis为0时，表示无限等待，直到被notify()或notifyAll()唤醒)。
	// 当前线程”在调用wait()时，必须拥有该对象的同步锁。该线程调用wait()之后，会释放该锁；
	// 然后一直等待直到“其它线程” 调用对象的同步锁的notify()或notifyAll()方法。然后，该线程继续等待直到它重新获取“该对象的同步锁”，然后就可以接着运行
	
	// 注意：jdk的解释中，说wait()的作用是让“当前线程”等待，而“当前线程”是指正在cpu上运行的线程！
	// 这也意味着，虽然t1.wait()是通过“线程t1”调用的wait()方法，但是调用t1.wait()的地方是在“主线程main”中
	// 而主线程必须是“当前线程”，也就是"运行状态"，才可以执行t1.wait()。
	    所以，此时的“当前线程”是“主线程main”！因此，t1.wait()是让“主线程”等待，而不是“线程t1”！
	 */


	/**
	 *
	 * 注意:如果我们把 ThreadWaitDemo 中run()中的 notify() 去 ，程序依然执行,这是为什么？
	 *  这里可能是以下原因：因为在这里 我们使用ti实例作为同步锁，在线程的run() 执行完后，进入死亡状态（这里为什么是死亡状态？因为运行完后就死亡了），
	 *  会调用线程实例的notifyAll()方法 也有可以能是（notify方法）。这个可以参考线程的join方法源码。
	 *  join方法的本质就是调用线程实例的wait方法，当线程执行完毕后，join方法会被唤醒，即wait方法被唤醒，本质上肯定调用过线程实例的notify方法。
	 *
	 *  可以在这里多建立一个线程，ThreadWaitDemo t2 =new ThreadWaitDemo("t2");
	 *  然后运行结果如下：我们可以先分析下代码执行后会产生的后果 ，在看从运行结果进行分析。
	 *
	 *  main start t1
	    main wait()
	    t2 call notify
	    t1 call notify
	    main continue

	    https://www.cnblogs.com/Hermioner/p/9853162.html 解释的比较清楚
	 */
}
