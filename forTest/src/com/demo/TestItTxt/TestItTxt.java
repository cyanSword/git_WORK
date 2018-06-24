package com.demo.TestItTxt;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yjf on 2018/4/24.
 */
public class TestItTxt {
    public static  void main(String[] args){
        try {
           URL one= ClassLoader.getSystemResource("com/demo/TestItTxt/demo.txt");
            BufferedReader reader =new BufferedReader(new FileReader(one.getFile()));

            List<String> words= reader.lines()
                    .flatMap(line-> Stream.of(line.split("\\s+")))
                    .filter(word->word.length()>0)
                    .peek(TestItTxt::out)           //peek:分流，在terminate操作之前再分一个流.与下面foreach得到结果一样
                    .collect(Collectors.toList());
            for(String ele:words){
                System.out.print(ele);
            }

            ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static  void out(String ele){
        System.out.print(ele);
    }


}
