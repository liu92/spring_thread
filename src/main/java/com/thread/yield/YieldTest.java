package com.thread.yield;

/**
 * 测试 yield()
 * <p>Title:YieldTest</p>
 * @author liuwanlin
 * @date 2018年1月2日上午11:16:27
 */
public class YieldTest {
  public static void main(String[] args) {
	  ThreadYield t1=new ThreadYield("t1");
	  ThreadYield t2=new ThreadYield("t2");
	  t1.start();
	  t2.start();
  }
    
    //  “线程t1”在能被4整数的时候，并没有切换到“线程t2”。这表明，
    //  yield()虽然可以让线程由“运行状态”进入到“就绪状态”；但是，它不一定会让其它线程获取CPU执行权(即，其它线程进入到“运行状态”)，
    //  即使这个“其它线程”与当前调用yield()的线程具有相同的优先级。
  
	//  t1 [5]:0
	//  t2 [5]:0
	//  t1 [5]:1
	//  t2 [5]:1
	//  t2 [5]:2
	//  t2 [5]:3
	//  t2 [5]:4
	//  t1 [5]:2
	//  t2 [5]:5
	//  t2 [5]:6
	//  t2 [5]:7
	//  t2 [5]:8
	//  t1 [5]:3
	//  t1 [5]:4
	//  t1 [5]:5
	//  t1 [5]:6
	//  t1 [5]:7
	//  t1 [5]:8
	//  t1 [5]:9
	//  t2 [5]:9

}
