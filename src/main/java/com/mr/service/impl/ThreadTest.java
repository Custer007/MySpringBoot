package com.mr.service.impl;

import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class ThreadTest implements Runnable{
    @Override
    public void run() {
        System.out.println(55);
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Thread thread  = new Thread(threadTest);
        List list = (List) Arrays.asList("apple", "banana", "pear", "orange");

        int[] arr = new int[]{1,2,3,4,5,6};
        IntStream streamed = Arrays.stream(arr);
        streamed.hashCode();

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

    }
}
