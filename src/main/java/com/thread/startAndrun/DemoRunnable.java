package com.thread.startAndrun;

  /**
   * java类简单作用描述
   * @ProjectName:    DemoRunnable.java
   * @Package:        com.thread.startAndrun
   * @ClassName:      DemoRunnable
   * @Description:    java类作用描述
   * @Author:         作者姓名
   * @CreateDate:     2018/10/30 0030 11:33
   * @UpdateRemark:   The modified content
   * @Version:        1.0
   */
public class DemoRunnable  implements Runnable{

	@Override
	public void run() {
		System.out.println("执行任务。。。。");
	}

}
