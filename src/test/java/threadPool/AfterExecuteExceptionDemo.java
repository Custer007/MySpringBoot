package threadPool;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 异常处理方式：重写 ThreadPoolExecutor.afterExecute()。
 * 适合在线程池层面统一兜底处理任务异常，比如统一日志、告警、监控。
 */
public class AfterExecuteExceptionDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                2,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100)
        ) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);

                if (t == null && r instanceof Future<?>) {
                    try {
                        ((Future<?>) r).get();
                    } catch (CancellationException e) {
                        t = e;
                    } catch (ExecutionException e) {
                        t = e.getCause();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (t != null) {
                    System.out.println("afterExecute 统一捕获异常: " + t.getMessage());
                }
            }
        };

        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 保存订单任务");
            throw new RuntimeException("保存订单失败");
        });

        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 发送短信任务");
            throw new RuntimeException("发送短信失败");
        });

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
