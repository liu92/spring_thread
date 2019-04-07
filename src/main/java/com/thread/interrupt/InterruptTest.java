package com.thread.interrupt;

/**
 * 测试  在t1的run()方法中，是在循环体while之外捕获的异常
 * <p>Title:InterruptTest</p>
 * @author liuwanlin
 * @date 2018年1月3日上午10:57:54
 */
public class InterruptTest {
	public static void main(String[] args) {
       try {
		 // 新建"t1"
	    Thread t1=new MyThread("t1");
	    System.out.println(t1.getName()+"("+t1.getState()+") is new.");
	   
	    t1.start();                  // 启动线程
	    System.out.println(t1.getName() +" ("+t1.getState()+") is started.");  
    	   
    	// 主线程休眠300ms，然后主线程给t1发“中断”指令。
		Thread.sleep(300);
		t1.interrupt();
		   
		System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");
		 // 主线程休眠300ms，然后查看t1的状态。
		
        Thread.sleep(300);
        System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");
        
	   } catch (InterruptedException e) {
		 e.printStackTrace();
	   }
      
	}

	/**
	//	t1(NEW) is new.
	//	t1 (RUNNABLE) is started.
	//	t1 (RUNNABLE) loop1
	//	t1 (RUNNABLE) loop2
	//	t1 (RUNNABLE) loop3
	//	t1 (RUNNABLE) is interrupted.
	//	t1 (TERMINATED) is interrupted now.
	*/

	/**
	 * 第一种：从打印可以看到：
	 *(01)主线程 main中 通过 new MyThread("t1")创建线程t1，之后通过t1.start启动线程 t1。
	 *(02)t1启动之后，会不断的检查它的中断标记，如果中断标记为“false”；则休眠100ms。
	 *(03)t1休眠后，会切换到主线程main;主线程再次运行时。会执行t1.interrupt()中断线程t1;
	 * t1收到指令后，会将t1的中断标记设置为false,
	 * 而且会抛出InterruptedException异常。在t1的run()方法中，是在循环体while之外捕获的异常；因此循环被终止。
	 *
	 *
	 * 第二种：在MyThread 类中 run() 方法添加break , 在退出循环后 又没有继续执行的代码，
	 * 所以线程应该是正常终止，而非调用interrupt()方法捕获异常终止
	 *
	 * 运行结果
	 * t1(NEW) is new.
	   t1 (RUNNABLE) is started.
	   try -->start=false
	   try -->while=false
	   t1 (RUNNABLE) loop 1
	   t1 (TERMINATED) is interrupted.
	   t1 (TERMINATED) is interrupted now.

	 也有可能是这样的情况：
	 在非阻塞状态下被中断了。由于run()里面除了sleep(100);之外，还有其他语句。
	 也有可能在执行run()方法的其他语句时被中断了。那么，在下一轮while循环时，while (!isInterrupted()) 就不成立。从而结束run()方法。
	 其实就是在 run()方法中 break 或者return

	  第三种 ：在MyThread 类中 run() 方法添加return , 在退出循环后 又没有继续执行的代码，所以线程应该是正常终止。

	   执行结果如下
	   t1(NEW) is new.
	   t1 (RUNNABLE) is started.
	   try -->start=false
	   try -->while=false
	   t1 (RUNNABLE) loop 1
	   t1 (TERMINATED) is interrupted.
	   t1 (TERMINATED) is interrupted now.
	 *
	 */
	
}
