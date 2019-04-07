/**
 * Copyright (C), 2018-2019, XXX有限公司
 * FileName: FinalReferenceDeom
 * Author:   lin
 * Date:     2019/2/21 23:33
 * Description: 测试final的重排序
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.fianlreference;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试final的重排序〉
 *
 * @author lin
 * @create 2019/2/21
 * @since 1.0.0
 */
public class FinalReferenceDeom {
    final  int i;
    static FinalReferenceDeom deom;


    /**1、在构造函数内对一个final域的写入,
     * 与随后把这个被构造对象的引用赋值给一个引用
     变量,这两个操作之间不能重排序
     2、.初次读一个包含final域的对象的引用，与随后初次读这个final域,这两个操作之间不能重排序。
     */

    public FinalReferenceDeom(){
        i=2;
    }

    public static void writer(){
        deom = new FinalReferenceDeom();
    }

    public static void reader() {
        if (deom != null) {
            int temp = deom.i;
        }
    }

}
