package com.thread.lock.conditionlock.one;
  /**
  * 普通 通过synchronized 测试 获取对象锁，
   * 并且比较Object 的wait(),notify(),notifyAll()和Condition 的await(),signal(),signalAll() 区别;
   *
  * @ProjectName:    WaitTest.java
  * @Package:        com.thread.lock.conditionlock
  * @ClassName:      WaitTest
  * @Description:    java类作用描述
  * @Author:         作者姓名
  * @CreateDate:     2018/11/29 0029 17:28
  * @UpdateUser:     作者姓名
  * @UpdateDate:     2018/11/29 0029 17:28
  * @UpdateRemark:   The modified content
  * @Version:        1.0
 */
public class WaitTest {


    public  static  void main(String [] args){
        ThreadA ta = new ThreadA("ta");
        synchronized (ta){
            try {
                System.out.println(Thread.currentThread().getName()+" start ta");
                ta.start();

                System.out.println(Thread.currentThread().getName()+" block");
                ta.wait();

                System.out.println(Thread.currentThread().getName()+" continue");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }

    static  class  ThreadA extends  Thread{

          ThreadA(String name){
             super(name);
        }

        @Override
        public void  run(){
            synchronized (this){
                System.out.println(Thread.currentThread().getName()+" workup others");
                notify();
            }
        }
    }


      /**
       * main start ta
         main block
         ta workup others
         main continue
       */
  }
