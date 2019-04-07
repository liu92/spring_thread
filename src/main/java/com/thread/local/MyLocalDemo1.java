/**
 * Copyright (C), 2018-2019, XXX有限公司
 * FileName:
 * Author:   lin
 * Date:     2019/1/14 22:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.thread.local;

/**
 * 〈一句话功能简述〉<br> 
 * 〈ThreadLocal 到底是什么〉
 *
 * @author lin
 * @create 2019/1/14
 * @since 1.0.0
 */
public class MyLocalDemo1 {

    public  static  void main(String [] agrs){

        ThreadLocal<String> localName = new ThreadLocal<>();
        localName.set("lin");
        localName.get();
    }
}
