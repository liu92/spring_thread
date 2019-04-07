package com.thread;

  /**
     *  java类简单作用描述
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
    private static volatile  DemoVolatile instance = null;

    public  static DemoVolatile getInstance(){
        if(instance == null){
            instance  = new DemoVolatile();
        }
        return  instance;
    }

      public static void main(String[] args) {
          DemoVolatile.getInstance();
      }
}
