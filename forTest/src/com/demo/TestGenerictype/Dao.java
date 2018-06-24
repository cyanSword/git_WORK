package com.demo.TestGenerictype;

/**
 * Created by yjf on 2018/5/3.
 */
public class Dao<T> {

    T oneDao=null;

    public Dao(T oneDao) {
        this.oneDao = oneDao;
    }

    public T getDao(){
        return  oneDao;
    }

    public  boolean IfGenIsTrue(){
        return  oneDao instanceof  Stu;
    }


}
