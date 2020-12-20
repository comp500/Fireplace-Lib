package the_fireplace.lib.api.multithreading;

import the_fireplace.lib.config.FireplaceLibConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ConcurrentExecutionManager {
    //Limit the number of active threads so we don't run the machine out of memory
    private static ExecutorService essentialExecutorService = Executors.newFixedThreadPool(FireplaceLibConfig.getInstance().getEssentialThreadPoolSize());
    private static ExecutorService nonessentialExecutorService = Executors.newFixedThreadPool(FireplaceLibConfig.getInstance().getNonEssentialThreadPoolSize());

    public static void run(Runnable runnable) {
        if(!essentialExecutorService.isShutdown())
            essentialExecutorService.execute(runnable);
    }

    public static void runKillable(Runnable runnable) {
        if(!nonessentialExecutorService.isShutdown())
            nonessentialExecutorService.execute(runnable);
    }

    public static void waitForCompletion() throws InterruptedException {
        essentialExecutorService.shutdown();
        nonessentialExecutorService.shutdownNow();
        essentialExecutorService.awaitTermination(1, TimeUnit.DAYS);
    }

    public static void startExecutors() {
        if (essentialExecutorService.isShutdown() || nonessentialExecutorService.isShutdown()) {
            try {
                waitForCompletion();
            } catch (InterruptedException ignored) {
            }
            essentialExecutorService = Executors.newFixedThreadPool(256);
            nonessentialExecutorService = Executors.newFixedThreadPool(128);
        }
    }
}
