package com.mr.temp;

import com.mr.interfaces.MyFunc;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class TestUnit {

    @Test
    public void test(){

        MyFunc func = s -> s + "踢世界杯";
        System.out.println(func.invoke("Custer"));

        // Integer转String
        Function<Integer, String> intToStr = num -> "数字：" + num;
        String result = intToStr.apply(66);
        System.out.println(result);

        // compose先执行、andThen后执行 链式处理
        Function<String, Integer> strToInt = Integer::parseInt;
        // 先转数字，再+10
        Function<String, Integer> addTen = strToInt.andThen(n -> n + 10);
        System.out.println(addTen.apply("20")); // 30
    }
}
