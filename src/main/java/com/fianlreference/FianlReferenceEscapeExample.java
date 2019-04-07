/**
 * Copyright (C), 2018-2019, XXX有限公司
 * FileName: FianlReferenceEscapeExample
 * Author:   lin
 * Date:     2019/2/21 23:38
 * Description: 在构造器内部，不能让这个被构造对象的引用为其他线程所见，也就是对象引用不能在构造函数中逸出
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.fianlreference;

/**
 * 〈一句话功能简述〉<br> 
 * 〈在构造器内部，不能让这个被构造对象的引用为其他线程所见，也就是对象引用不能在构造函数中逸出〉
 *
 * @author lin
 * @create 2019/2/21
 * @since 1.0.0
 */
public class FianlReferenceEscapeExample {
    final  int i;
    static  FianlReferenceEscapeExample obj;

    public  FianlReferenceEscapeExample(){
        /**
         第1步
         */
        i = 1;
        /**
         第2步
         */
        obj = this;
    }

    public static  void writer(){
        new FianlReferenceEscapeExample();
    }

    public static void reader(){
        if(obj != null){
           int temp = obj.i;
        }
    }

    /**
      上面的问题是 在 多线程情况下 线程A 执行writer ,线程B执行reader ,
      在 1 和 2 这两步是 可能会出现重排序问题 ，也就是在 i还没有初始化好对象就逸出了
     */

}
