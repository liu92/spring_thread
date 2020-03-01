package com.clonede;

/**
 *
 * 　大家先思考一个问题，为什么需要克隆对象？直接new一个对象不行吗？

 　　答案是：克隆的对象可能包含一些已经修改过的属性，而new出来的对象的属性都还是初始化时候的值，
 所以当需要一个新的对象来保存当前对象的“状态”就靠clone方法了。那么我把这个对象的临时属性一个一个的赋值给我新new的对象不也行嘛？
 可以是可以，但是一来麻烦不说，二来，大家通过上面的源码都发现了clone是一个native方法，就是快啊，在底层实现的。

 　　提个醒，我们常见的Object a=new Object();Object b;b=a;这种形式的代码复制的是引用，即对象在内存中的地址，a和b对象仍然指向了同一个对象。

 　　而通过clone方法赋值的对象跟原来的对象时 同时独立存在的。
 * @ClassName: Student
 * @Description:
 * @Author: lin
 * @Date: 2019/10/10 23:53
 * History:
 * @<version> 1.0
 */
public class Student implements Cloneable {
    /**
     * 浅克隆(ShallowClone)
     */

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() {
        Student stu = null;
        try{
            stu = (Student)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
}
