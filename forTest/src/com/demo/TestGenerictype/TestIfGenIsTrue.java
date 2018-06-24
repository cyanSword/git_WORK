package com.demo.TestGenerictype;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by yjf on 2018/5/3.
 */
public class TestIfGenIsTrue {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //  StuDao temp=new StuDao(new Stu());
        //  System.out.print(temp.IfGenIsTrue());
//


        //  Dao temp1=new Dao();
        // System.out.print(temp1.IfGenIsTrue());


        ArrayList<String> arrayList1 = new ArrayList<String>();

        arrayList1.add("abc");

        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();

        arrayList2.add(123);

        System.out.println(arrayList1.getClass() == arrayList2.getClass());


        ArrayList<Integer> arrayList3 = new ArrayList<Integer>();

        arrayList3.add(1);//这样调用add方法只能存储整形，因为泛型类型的实例为Integer  

        arrayList3.getClass().getMethod("add", Object.class).invoke(arrayList3, "asd");

        for (int i = 0; i < arrayList3.size(); i++) {

            System.out.println(arrayList3.get(i));
        }


    }
}
