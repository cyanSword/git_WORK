package com.demo.TestClone;

/**
 * Created by yjf on 2018/5/14.
 */
public class Demo implements Cloneable{
    private String id;
    private Integer index;

    public  Object clone() throws CloneNotSupportedException {
        Object Clodemo=null;
        try {
            Clodemo=super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return Clodemo;
    }


    public Demo() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Demo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }

    public Demo(String id, Integer index) {
        this.id = id;
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
