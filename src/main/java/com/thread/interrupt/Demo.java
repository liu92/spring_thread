package com.thread.interrupt;

/**
 * 下面是通过“额外添加标记”的方式终止“状态状态”的线程的示例：
 * <p>Title:Demo</p>
 * @author liuwanlin
 * @date 2018年1月3日下午6:03:30
 */
public class Demo {
	public static void main(String[] args) {
		
        try {
			// 新建“线程t1”
        	DemoThread t1=new DemoThread("t1");
    		System.out.println(t1.getName() +" ("+t1.getState()+") is new.");  
    		
    		t1.start();
    		System.out.println(t1.getName() +" ("+t1.getState()+") is started.");  

    		// 主线程休眠300ms，然后主线程给t1发“中断”指令。
			Thread.sleep(300);
			
			t1.stopTask();
		    System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");
		    
		    // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(300);
            System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
      

		/**
		 *
		 第一种 ：不在 run() 方法中while循环处添加 break 和 return 执行结果如下
		 t1 (NEW) is new.
		 t1 (RUNNABLE) is started.
		 t1 (RUNNABLE) loop 1
		 t1 (RUNNABLE) loop 2
		 t1 (RUNNABLE) is interrupted.
		 t1 (RUNNABLE) loop 3
		 t1 (TERMINATED) is interrupted now.


		 第二种：如果在while 循环中添加 break 会怎么样喃，来看看运行结果
		 t1 (NEW) is new.
		 t1 (RUNNABLE) is started.
		 t1 (RUNNABLE) loop 1
		 t1 (TERMINATED) is interrupted.
		 t1 (TERMINATED) is interrupted now.
            从上 结果看出 线程在 第一次执行循环后跳出循环 不在执行，因为在循环后没有继续执行的代码，所以最后线程是正常终止


		 第二种：如果在while 循环中 return 会怎么样， 同样看看运行结果
		 t1 (NEW) is new.
		 t1 (RUNNABLE) is started.
		 t1 (RUNNABLE) loop 1
		 t1 (TERMINATED) is interrupted.
		 t1 (TERMINATED) is interrupted now.
          从运行结果可以看出 线程同样是在第一次执行循环 ，因为在循环后没有继续执行的代码，所以最后线程是正常终止

		 */

		/**
		 * 最后谈谈 interrupted() 和 isInterrupted()。
		 * interrupted() 和
		 * isInterrupted()都能够用于检测对象的“中断标记”。
		 * 区别是，interrupted()除了返回中断标记之外，它还会清除中断标记
		 * (即将中断标记设为false)；而isInterrupted()仅仅返回中断标记。
		 */
	}
}
