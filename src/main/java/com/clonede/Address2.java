package com.clonede;

/**
 * @ClassName: Address2
 * @Description:
 * @Author: lin
 * @Date: 2019/10/11 8:24
 * History:
 * @<version> 1.0
 */
public class Address2 implements  Cloneable{
    private String add;
    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public Object clone() {
        Address2 addr = null;
        try {
            addr = (Address2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return addr;
    }
}
