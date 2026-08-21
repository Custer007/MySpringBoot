package com.mr.temp;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 測試并发线程
 * @Author: Han-Bo
 */
public class HashMapConcurrentPutDemo {
    // 非线程安全HashMap
    private static final HashMap<String, Integer> map = new HashMap<>();
    // 并发线程数
    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            int finalI = i;
            new Thread(() -> {
                // 所有线程操作同一个key："num"，value为线程编号
                map.put("num", finalI);
                latch.countDown();
            }).start();
        }

        // 等待所有线程执行完毕
        latch.await();
        System.out.println("最终map中key=num对应的value：" + map.get("num"));
        System.out.println("预期：最终值应该是99，实际大概率不是");
    }
}