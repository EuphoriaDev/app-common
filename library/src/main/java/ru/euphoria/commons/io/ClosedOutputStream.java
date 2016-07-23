package ru.euphoria.commons.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Closed output stream. This stream throws an exception on all attempts to
 * write something to the stream.
 * <p>
 * Typically uses of this class include testing for corner cases in methods
 * that accept an output stream and acting as a sentinel value instead of
 * a {@code null} output stream.
 *
 * @since 1.0
 */
public class ClosedOutputStream extends OutputStream {


    /**
     * Throws an {@link IOException} to indicate that the stream is closed.
     *
     * @param oneByte ignored
     * @throws IOException always thrown
     */
    @Override
    public void write(int oneByte) throws IOException {
        throw new IOException("failed to write byte: stream is closed");
    }
}