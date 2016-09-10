package ru.euphoria.commons.io;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.euphoria.commons.Main;

/**
 * Wraps an existing {@link InputStream} and perform limited reading from.
 *
 * @since 1.0
 */
public class LimitedInputStream extends FilterInputStream {
    private long left;

    public LimitedInputStream(InputStream in, long limit) {
        super(in);
        if (limit <= 0) {
            throw new IllegalArgumentException("limit can't be negative: " + limit);
        }
        this.left = limit;
    }

    @Override
    public int available() throws IOException {
        return (int) Math.min(super.available(), left);
    }

    @Override
    public int read() throws IOException {
        if (left == 0) {
            return -1;
        }

        int result = super.read();
        if (result != -1) {
            left--;
        }
        return result;
    }

    @Override
    public int read(byte[] buffer, int offset, int count) throws IOException {
        if (left == 0) {
            return -1;
        }

        int length = (int) Math.min(count, left);
        int result = super.read(buffer, offset, length);
        if (result != -1) {
            left -= length;
        }

        return result;
    }

    @Override
    public long skip(long count) throws IOException {
        long n = Math.min(count, left);
        long skipped = super.skip(n);
        left -= skipped;

        return skipped;
    }
}
