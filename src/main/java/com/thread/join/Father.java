package com.thread.join;

/**
 * 08：
 *  join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行。
 *  现在我们通过示例去理解
 *  
 *  主线程
 * <p>Title:Father</p>
 * @author liuwanlin
 * @date 2018年1月2日下午3:33:58
 */
public class Father extends Thread{

   @Override
   public void run(){
	try {
	   Son s =new Son();
	   s.start();
	   s.join();


//		System.out.printf("%s start\n",this.getName());
//	    for (int i = 0; i < 5; i++) {
//		 System.out.printf("%s finish\n",this.getName());
//	    }

	} catch (InterruptedException e) {
		e.printStackTrace();
	}
   }

//   public Father(String name){
//   	 super(name);
//   }
	
   
  /**
   *  上面的有两个类Father(主线程类)和Son(子线程类)。因为Son是在Father中创建并启动的，所以，Father是主线程类，Son是子线程类。
   *  在Father主线程中，通过new Son()新建“子线程s”。接着通过s.start()启动“子线程s”，
   *  并且调用s.join()。在调用s.join()之后，Father主线程会一直等待，直到“子线程s”运行完毕；
   *  在“子线程s”运行完毕之后，Father主线程才能接着运行。 这也就是我们所说的“join()的作用，是让主线程会等待子线程结束之后才能继续运行”！
   */
   
   /**
    * 问题：虽然s.join()被调用的地方是发生在“Father主线程”中，
    * 但是s.join()是通过“子线程s”去调用的join()。
    * 那么，join()方法中的isAlive()应该是判断“子线程s”是不是Alive状态；
    * 对应的wait(0)也应该是“让子线程s”等待才对。但如果是这样的话，s.join()的作用怎么可能是“让主线程等待，
    * 直到子线程s完成为止”呢，应该是让"子线程等待才对(因为调用子线程对象s的wait方法嘛)"？
    */
   
   /**
    * 答案：wait()的作用是让“当前线程”等待，而这里的“当前线程”是指当前在CPU上运行的线程。
    * 所以，虽然是调用子线程的wait()方法，但是它是通过“主线程”去调用的；所以，休眠的是主线程，而不是“子线程”！
    */
}
