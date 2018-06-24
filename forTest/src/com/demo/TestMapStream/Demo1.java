package com.demo.TestMapStream;

/**
 * Created by yjf on 2018/4/23.
 */
public class Demo1 {
    private  String id;
    private  String name;
    private  String acc;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Demo1{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", acc='").append(acc).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Demo1(String id, String name, String acc) {
        this.id = id;
        this.name = name;
        this.acc = acc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }
}
