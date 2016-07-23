package ru.euphoria.commons.concurrent;


import android.os.Process;

/**
 * Created by user on 28.02.16.
 * <p/>
 * Thread with low priority. So that it will have less chance of impacting
 * the responsiveness of the user interface, but speed of work is decreased.
 *
 * @since 1.0
 */
public class LowThread extends Thread {

    /**
     * Constructs a new {@code Thread} with a {@code Runnable} object and a
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
        // Sets the background Priority
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        super.run();
    }
}
