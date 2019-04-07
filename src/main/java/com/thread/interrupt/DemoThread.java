package com.thread.interrupt;

/**
 * 下面是通过“额外添加标记”的方式终止“状态状态”的线程的示例：
 * <p>Title:DemoThread</p>
 * @author liuwanlin
 * @date 2018年1月3日下午6:03:24
 */
public class DemoThread extends Thread{
    private volatile boolean flag=true;
  
	public void stopTask(){
		flag=false; 
	}
	
	public DemoThread(String name){
		super(name);
	}
	
	
	@Override
	public void run(){
		synchronized (this) {
			try {
				int i=0;
				while (flag) {
					Thread.sleep(100);
					i++;
					System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") loop " + i);
//					break;
//					return;
				}

			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
			}
		}
	}
}
