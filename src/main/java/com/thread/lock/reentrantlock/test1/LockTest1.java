package com.thread.lock.reentrantlock.test1;
/**
 * 测试独占锁
 * <p>Title:LockTest1</p>
 * @author liuwanlin
 * @date 2018年2月12日下午5:57:17
 */
public class 	LockTest1 {
  public static void main(String[] args) {
	Depot depot = new Depot();
	Producer producer = new Producer(depot);
	Customer customer = new Customer(depot);

	  // 生产60个
	producer.produce(60);
	  // 再生产120
	producer.produce(120);
	  // 消费150
	customer.consume(150);
	  // 再消费 90 （这里会出现负数）
	customer.consume(90);
	producer.produce(110);
  }
  
	/**
	 *
	 * 运行结果
	 *pool-1-thread1 produce(60) --> size=60
	  pool-2-thread1 produce(120) --> size=180
	  pool-3-thread1 consume(150) <-- size=30
	  pool-4-thread1 consume(90) <-- size=-60
	  pool-5-thread1 produce(110) --> size=50
	 *
	 *
	 *
	 * (01) Depot
	 * 是个仓库。通过produce()能往仓库中生产货物，通过consume()能消费仓库中的货物。通过独占锁lock实现对仓库的互斥访问
	 * ：在操作(生产/消费)仓库中货品前，会先通过lock()锁住仓库，操作完之后再通过unlock()解锁。
	 * 
	 *  (02)
	 * Producer是生产者类。调用Producer中的produce()函数可以新建一个线程往仓库中生产产品。
	 *  (03)
	 * Customer是消费者类。调用Customer中的consume()函数可以新建一个线程消费仓库中的产品。
	 * 
	 *  (04)
	 * 在主线程main中，我们会新建1个生产者producer，同时新建1个消费者customer。它们分别向仓库中生产/消费产品。
	 * 根据main中的生产/消费数量，仓库最终剩余的产品应该是50。运行结果是符合我们预期的！
	 */
  
    /**
    * 这个模型存在两个问题：
      (01) 现实中，仓库的容量不可能为负数。但是，此模型中的仓库容量可以为负数，这与现实相矛盾！
      (02) 现实中，仓库的容量是有限制的。但是，此模型中的容量确实没有限制的！
                这两个问题，我们稍后在去学习如何解决。现在，先看个简单的示例2；通过对比“示例1”和“示例2”,我们能更清晰的认识lock(),unlock()的用途。
     * 
     */

}
