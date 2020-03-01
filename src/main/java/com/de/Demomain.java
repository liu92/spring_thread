/**
 * Copyright (C), 2018-2019, XXX有限公司
 * FileName: Demomain
 * Author:   lin
 * Date:     2019/2/20 22:40
 * Description: 测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.de;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试〉
 *
 * @author lin
 * @create 2019/2/20
 * @since 1.0.0
 */
public class Demomain {
    public static void main(String[] args) {
//        DemoProt   prot = new DemoProt();
//
//        System.out.println(prot.getClass().getClassLoader());

        Object[] t = {10, 3 ,5, 9};

        System.out.println("获取class--------"+t.getClass());
        // 获取class--------class [Ljava.lang.Object;
        System.out.println("获取class的componentType--------"+t.getClass().getComponentType());


        Object object = "34";
        System.out.println("不是数组获取class的componentType--------"+object.getClass().getComponentType());
        //不是数组获取class的componentType--------null
    }
}
