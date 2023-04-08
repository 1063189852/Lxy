package lxy.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo1 {


    // 核心线程数
    public static final int CORE_SIZE = 2;

    //
    public static final int MAX_SIZE = 5;


    /**
     * 线程池的7个参数
     * int corePoolSize 核心线程数
     * int maximumPoolSize 最大线程数
     * long keepAliveTime 最大线程数的空闲时间
     * TimeUnit unit //活跃时间的单位
     * BlockingQueue<Runnable> workQueue 阻塞队列
     * ThreadFactory threadFactory 线程工程
     * RejectedExecutionHandler handler 拒绝策略
     */
    public static void main(String[] args) throws InterruptedException {
        // 使用 ThreadFactoryBuilder 创建自定义线程名称的 ThreadFactory
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("hyn-demo-pool-%d").build();

        // 创建线程池，其中任务队列需要结合实际情况设置合理的容量
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_SIZE,
                MAX_SIZE,
                5L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        // 新建 1000 个任务，每个任务是打印当前线程名称
        for (int i = 0; i < 10; i++) {
//            executor.execute(() -> System.out.println(Thread.currentThread().getName()));

            executor.execute(() -> {
                try {
                    Thread.sleep(1001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            executor.execute(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            executor.execute(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });


        }
        // 优雅关闭线程池
        executor.shutdown();
        executor.awaitTermination(1000L, TimeUnit.SECONDS);
        // 任务执行完毕后打印"Done"
        System.out.println("Done");
    }

}
