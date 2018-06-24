package com.demo.TestMapStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yjf on 2018/4/23.
 */
public class TestMapStreamHandle {
    static     int i=0;
    public static  void main(String[] args){

        List<Demo> list=new ArrayList<>();
        List<Demo1> list1=new ArrayList<>();
        list.add(new Demo("123","jack"));
        list.add(new Demo("456","joe"));
        list1=   list.stream().map((one)->{
            return   new Demo1(one.getId(),one.getName(),""+(i++));
        }).collect(Collectors.toList());
        System.out.print(list1);
    }

}
