package com.clonede;

/**
 * @ClassName: Student2
 * @Description:
 * @Author: lin
 * @Date: 2019/10/11 8:25
 * History:
 * @<version> 1.0
 */
public class Student2 implements Cloneable {

    private int number;
    private Address2 addr;

    public Address2 getAddr() {
        return addr;
    }

    public void setAddr(Address2 addr) {
        this.addr = addr;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() {
        Student2 stu = null;
        try {
            //浅复制
            stu = (Student2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //深度复制
        stu.addr = (Address2) addr.clone();
        return stu;
    }
}
