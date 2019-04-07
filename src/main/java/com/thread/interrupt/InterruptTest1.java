package com.thread.interrupt;
/**
 * 将run()方法中捕获InterruptedException异常的代码块移到while循环体内
 * <p>Title:InterruptTest1</p>
 * @author liuwanlin
 * @date 2018年1月3日上午10:57:42
 */
public class InterruptTest1 {

	public static void main(String[] args) {
		try {
			// 新建线程t1
			Thread t1=new MyThread1("t1");
			System.out.println(t1.getName()+" ("+t1.getState()+") is new.");
			
			t1.start();        // 启动“线程t1”
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
		

		/**
		// 运行结果： 多运行几次就会出现 死循环


		//t1 (NEW) is new.
		//t1 (RUNNABLE) is started.
		//t1 (RUNNABLE) loop 1
		//t1 (RUNNABLE) loop 2
		//t1 (TIMED_WAITING) is interrupted.
		//t1 (RUNNABLE) catch InterruptedException.
		//t1 (RUNNABLE) loop 3
		//t1 (RUNNABLE) loop 4
		//t1 (RUNNABLE) loop 5
		//t1 (RUNNABLE) loop 6
		//t1 (RUNNABLE) is interrupted now.
		//t1 (RUNNABLE) loop 7
		//t1 (RUNNABLE) loop 8
		//t1 (RUNNABLE) loop 9
		//t1 (RUNNABLE) loop 10
		//t1 (RUNNABLE) loop 11
		
	    // 出现死循环的标志：是因为线程"t1"在等待(阻塞)状态时，被interrupt终止了；此时，会清除中断标记（即isInterrupted()会返回false）
		// 而且会抛出InterruptedException异常[该异常在while循环体内被捕获]。因此，t1理所当然的会进入死循环了。
		// 解决该问题，需要我们在捕获异常时，额外的进行退出while循环的处理。
		// 例如，在MyThread的catch(InterruptedException)中添加break 或 return就能解决该问题。
		 */
		
	}
}
