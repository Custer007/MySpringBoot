package threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 异常处理方式：任务内部 try-catch。
 * 适合业务任务自己处理失败逻辑，比如记录日志、重试、补偿、保存失败记录。
 */
public class SimpleThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100)
        );

        for (int i = 1; i <= 5; i++) {
            int taskNo = i;
            executor.execute(() -> runTask(taskNo));
        }

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }

    private static void runTask(int taskNo) {
        try {
            System.out.println(Thread.currentThread().getName() + " 开始执行任务 " + taskNo);

            if (taskNo == 3) {
                throw new RuntimeException("模拟任务 " + taskNo + " 执行失败");
            }

            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(Thread.currentThread().getName() + " 任务 " + taskNo + " 执行成功");
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " 捕获任务异常: " + e.getMessage());
        }
    }
}
