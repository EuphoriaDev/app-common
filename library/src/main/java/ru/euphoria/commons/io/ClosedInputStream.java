package ru.euphoria.commons.io;

import java.io.InputStream;

/**
 * Closed input stream. This stream returns EOF to all attempts to read
 * something from the stream.
 *
 * Typically uses of this class include testing for corner cases in methods
 * that accept input streams and acting as a sentinel value instead of a
 * {@code null} input stream.
 *
 * @since 1.0
 */
public class ClosedInputStream extends InputStream {
    public static final ClosedInputStream INSTANCE = new ClosedInputStream();

    /**
     * Returns -1 to indicate that the stream is closed.
     *
     * @return always -1
     */
    @Override
    public int read() {
        return -1;
    }

}