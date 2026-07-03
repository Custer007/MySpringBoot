package threadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 异常处理方式：CompletableFuture.exceptionally()。
 * 适合异步任务链路中发生异常后返回兜底结果。
 */
public class CompletableFutureExceptionDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                2,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100)
        );

        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " 查询商品信息");
                    if (true) {
                        throw new RuntimeException("商品服务异常");
                    }
                    return "商品信息";
                }, executor)
                .exceptionally(e -> {
                    System.out.println("exceptionally 捕获异常: " + e.getMessage());
                    return "默认商品信息";
                });

        System.out.println("最终结果: " + future.join());

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
