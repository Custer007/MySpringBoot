package threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 异常处理方式：RejectedExecutionHandler 拒绝策略。
 * 适合处理线程池和队列都满了，任务提交失败的场景。
 */
public class RejectedExecutionHandlerDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                (task, pool) -> System.out.println("拒绝策略捕获提交失败的任务: " + task)
        );

        for (int i = 1; i <= 5; i++) {
            int taskNo = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 执行任务 " + taskNo);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
