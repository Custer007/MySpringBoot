package threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/*
 * 异常处理方式：submit() + Future.get()。
 * submit() 提交的任务异常会被封装到 Future 中，需要调用 get() 才能拿到真实异常。
 */
public class FutureGetExceptionDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                2,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100)
        );

        Future<String> successFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 查询用户信息");
            TimeUnit.MILLISECONDS.sleep(300);
            return "用户信息";
        });

        Future<String> failFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 查询订单信息");
            throw new RuntimeException("订单服务异常");
        });

        getResult("用户任务", successFuture);
        getResult("订单任务", failFuture);

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }

    private static void getResult(String taskName, Future<String> future) {
        try {
            String result = future.get();
            System.out.println(taskName + " 执行成功: " + result);
        } catch (ExecutionException e) {
            System.out.println(taskName + " 执行失败: " + e.getCause().getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(taskName + " 被中断");
        }
    }
}
