package com.thread.atomic.demoreference;
  /**
     *  java类简单作用描述
     * @ProjectName:    Preson.java
     * @Package:        com.thread.atomic.demoreference
     * @ClassName:      Preson
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/23 0023 11:35
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/23 0023 11:35
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class Preson {

     private volatile  long id;

     public  Preson(long id){
         this.id = id;
     }

      @Override
      public String toString() {
          return "id"+id;
      }
  }
