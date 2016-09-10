package ru.euphoria.commons.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Simple thread executor
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class ThreadExecutor {
    /** Number of processor cores available */
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /** Thread executor for execute on background with low priority */
    private static final Executor cpuExecutor = Executors.newFixedThreadPool(CPU_COUNT + 1, new PriorityThreadFactory());

    /**
     * Execute runnable with {@link Executor} on {@link LowThread}
     *
     * @param command is the code you need to execute in a background
     */
    public static void execute(Runnable command) {
        new LowThread(command).start();
    }

    /**
     * Execute runnable with {@link Executor} on cpu threads
     *
     * @param command the code to execute in a background
     */
    public static void executeOnCpu(Runnable command) {
        cpuExecutor.execute(command);
    }

    /**
     * Thread factory for execute thread with low priority
     */
    public static class PriorityThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new LowThread(r);
        }
    }
}