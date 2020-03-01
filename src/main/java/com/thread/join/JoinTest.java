package com.thread.join;

/**
 * 测试 join()
 * <p>Title:JoinTest</p>
 * @author liuwanlin
 * @date 2018年1月2日下午4:14:27
 */
public class JoinTest {

	 public static void main(String[] args) {// 主线程
 		try {
			// 新建线程“t1”
 			ThreadA t1=new ThreadA("t1");
			// 启动 “线程t1”
 	 		t1.start();
			// 将线程"t1" 加入到 "主线程 main" 中，并且"主线程" main() 会等待它的 完成。
			t1.join();
			System.out.printf("%s finish\n", Thread.currentThread().getName());
			System.out.println("当前线程的状态-------------"+Thread.currentThread().getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}                  
	}
	 
	 static class ThreadA extends Thread {
		 public ThreadA(String name){
			super(name); 
		 }
		 
		 @Override
		 public void run(){
			 System.out.printf("%s start\n",this.getName());
			 for (int i = 0; i < 1000000; i++) {
				 System.out.printf("%s finish\n",this.getName());
			 }

		 }
	 }
	 
     //(01) 在“主线程main”中通过 new ThreadA("t1") 新建“线程t1”。 接着，通过 t1.start() 启动“线程t1”，并执行t1.join()。
     //(02) 执行t1.join()之后，“主线程main”会进入“阻塞状态”等待t1运行结束。
	// “子线程t1”结束之后，会唤醒“主线程main”，“主线程”重新获取cpu执行权，继续运行。
	 
	 
	 
	 //t1 start
     //t1 finish
     //main finish

}
