package ru.euphoria.commons.concurrent;


import android.os.Process;

/**
 * Thread with low priority. So that it will have less chance of impacting
 * the responsiveness of the user interface, but speed of work is decreased.
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class LowThread extends Thread {
    /**
     * Constructs a new {@code LowThread} with a {@code Runnable} object and a
     * newly generated name. The new {@code Thread} will belong to the same
     * {@code ThreadGroup} as the {@code Thread} calling this constructor.
     *
     * @param runnable a whose method <code>run</code> will be
     *                 executed by the new {@code Thread}
     * @see ThreadGroup
     * @see Runnable
     */
    public LowThread(Runnable runnable) {
        super(runnable);
    }

    /**
     * Constructs a new {@code Thread}
     */
    public LowThread() {
        super();
    }

    @Override
    public void run() {
        // using the background priority
        // for smooth user interface
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        super.run();
    }
}
