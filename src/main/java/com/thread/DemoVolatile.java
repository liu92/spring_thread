package com.thread;


/**
     *
     * @ProjectName:    DemoVolatile.java
     * @Package:        com.thread
     * @ClassName:      DemoVolatile
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/15 0015 17:05
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/15 0015 17:05
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class DemoVolatile {

   private static volatile  boolean initFlag = false;

   /**
    *
    */
    static void refresh(){
        System.out.println("refresh data......");
        initFlag = true;
        System.out.println("refresh data success");
   }


    static  void  loadData(){
       while (!initFlag){

       }
       System.out.println("线程：" + Thread.currentThread().getName() +"" +
                " 当前线程已经知道initFlag的状态改变");
   }

      public static void main(String[] args) {
          Thread threadA = new Thread(()-> loadData(),"threadA");
          threadA.start();

          try {
              Thread.sleep(500);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          Thread threadB = new Thread(()-> refresh(), "threadB");
          threadB.start();
      }


      
}
