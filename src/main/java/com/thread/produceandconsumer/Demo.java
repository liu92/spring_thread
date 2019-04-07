package com.thread.produceandconsumer;

/**
 * 测试生产者和消费者
 * <p>Title:Demo</p>
 * @author liuwanlin
 * @date 2018年1月12日下午3:56:21
 */
public class Demo {

	public static void main(String[] args) {
		Depot depot=new Depot(100);
		
		Producer producer =new Producer(depot);
		
		Customer customer =new Customer(depot);
		
		producer.produce(60);
		producer.produce(120);
		customer.consume(90);
		customer.consume(150);
		producer.produce(110);
	}
	
	/**
	 * 打印结果如下
	 */
	//	Thread-0 produce( 60) --> left=  0, inc= 60, size= 60
	//	Thread-2 consume( 90) <-- left= 30, dec= 60, size=  0
	//	Thread-4 produce(110) --> left= 10, inc=100, size=100
	//	Thread-3 consume(150) <-- left= 50, dec=100, size=  0
	//	Thread-1 produce(120) --> left= 20, inc=100, size=100
	//	Thread-2 consume( 90) <-- left=  0, dec= 30, size= 70
	//	Thread-4 produce(110) --> left=  0, inc= 10, size= 80
	//	Thread-1 produce(120) --> left=  0, inc= 20, size=100
	//	Thread-3 consume(150) <-- left=  0, dec= 50, size= 50
	
	/**
	 * 1、从代码可知 Producer 是生产者类，他与仓库（depot）关联。当调用“生产者”的produce()方法时，它会新建一个线程并向“仓库”中生产产品。
	 * 2、而 Customer 则是 消费者类，他与仓库（depot）关联。当调用的consume()方法时。它会新建一个线程消费“仓库”中的产品。
	 * 3、Depot 是 仓库类，仓库中记录“仓库的容量（capacity）”以及“仓库中当前产品数目(size)
	 * 
	 * “仓库”类的生产方法 produce（）和消费方法consume（）都是 synchronized 方法。进入 synchronized方法体，就意味着这个线程取得了该“仓库”对象的同步锁。
	 * 这也是就是说。同一时间，生产者和消费者线程只能有一个运行。通过同步锁 ，实现了对“残酷”的互斥访问。
	 * 
	 * （这里说明下为什么 使用对象的同步锁   互斥访问）
	 *     
	 * 
	 *  对于生产方法produce()而言：当仓库满时，生产者线程等待，需要等待消费者消费产品之后，
	 *  生产线程才能生产；生产者线程生产完产品之后，会通过notifyAll()唤醒同步锁上的所有线程，包括“消费者线程”，即我们所说的“通知消费者进行消费”。
	 *  
     *  对于消费方法consume()而言：当仓库为空时，消费者线程等待，需要等待生产者生产产品之后，
     *  消费者线程才能消费；消费者线程消费完产品之后，会通过notifyAll()唤醒同步锁上的所有线程，包括“生产者线程”，即我们所说的“通知生产者进行生产”。
	 *
	 *
	 *
	 *
	 */

}
