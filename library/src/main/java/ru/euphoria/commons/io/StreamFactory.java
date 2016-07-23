package ru.euphoria.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Provides utility methods for creating buffer and stream for working with I/O
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class StreamFactory {
    /** The default buffer size, 8K bytes */
    public static final int BUFFER_SIZE = 8192;
    /** The default char buffer size with 2K chars (4K bytes) */
    public static final int CHAR_BUFFER_SIZE = 2048;

    /**
     * Creates a new {@link ByteBuffer} for buffering reads or writes
     */
    public static ByteBuffer createBuffer() {
        return ByteBuffer.allocate(BUFFER_SIZE);
    }

    /**
     * Creates a new {@code CharBuffer} for buffering reads or writes
     */
    public static CharBuffer createCharBuffer() {
        return CharBuffer.allocate(CHAR_BUFFER_SIZE);
    }

    public static BufferedInputStream createBuffered(InputStream input) {
        return input instanceof BufferedInputStream ? (BufferedInputStream) input
                : new BufferedInputStream(input, BUFFER_SIZE);
    }

    public static BufferedOutputStream createBuffered(OutputStream output) {
        return output instanceof BufferedOutputStream ? (BufferedOutputStream) output
                : new BufferedOutputStream(output, BUFFER_SIZE);
    }

    public static BufferedReader createBuffered(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader
                : new BufferedReader(reader);
    }

    public static BufferedWriter createBuffered(Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer
                : new BufferedWriter(writer);
    }

    public static BufferedReader createReader(File file) throws FileNotFoundException {
        return createBuffered(new FileReader(file));
    }

    public static BufferedWriter createWriter(File file, boolean append, Charset charset) throws FileNotFoundException {
        return createBuffered(new OutputStreamWriter(new FileOutputStream(file, append), charset));
    }

    public static BufferedWriter createWriter(File file) throws FileNotFoundException {
        return createWriter(file, false, Charsets.UTF_8);
    }


}