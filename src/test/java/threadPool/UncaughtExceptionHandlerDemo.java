package threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 异常处理方式：Thread.UncaughtExceptionHandler。
 * 适合处理 execute() 提交任务时没有被捕获的运行时异常。
 */
public class UncaughtExceptionHandlerDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                2,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new NamedThreadFactory()
        );

        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " execute 任务开始");
            throw new RuntimeException("execute 未捕获异常");
        });

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }

    private static class NamedThreadFactory implements ThreadFactory {
        private final AtomicInteger index = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("handler-pool-" + index.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, e) ->
                    System.out.println("UncaughtExceptionHandler 捕获异常: "
                            + t.getName() + ", " + e.getMessage())
            );
            return thread;
        }
    }
}
