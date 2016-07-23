package ru.euphoria.commons.io;

import android.os.Build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;

import static ru.euphoria.commons.io.StreamFactory.BUFFER_SIZE;
import static ru.euphoria.commons.io.StreamFactory.CHAR_BUFFER_SIZE;

/**
 * Provides static utils methods for manipulation with the file system.
 * e.g. read, write, copy and move.
 *
 * Example to init api and execute users.get request:
 * <pre>
 *     // Read all lines from file
 *     String lines = FileStreams.read(temp);
 *
 *     // Read bytes from connection stream
 *     byte[] array = FileStreams.readBytes(connection.getInputStream());
 * </pre>
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class FileStreams {
    private static final char lineSeparatorChar = lineSeparator().charAt(0);

    public static final int ONE_KB = 1024;
    public static final int ONE_MB = ONE_KB * 1024;
    public static final int ONE_GB = ONE_MB * 1024;

    public static final long ONE_TB = ONE_GB * 1024L;
    public static final long ONE_PB = ONE_TB * 1024L;
    public static final long ONE_EB = ONE_PB * 1024L;

    public static final BigInteger ONE_ZB = BigInteger.valueOf(ONE_EB).multiply(BigInteger.valueOf(1024L));
    public static final BigInteger ONE_YB = ONE_ZB.multiply(BigInteger.valueOf(1024L));;

    // only static methods
    private FileStreams() {}

    /**
     * Reads all characters from specified file
     *
     * @param from the file to read from
     * @throws IOException if an I/O error occurs reading from the stream
     */
    public static String read(File from) throws IOException {
        BufferedReader reader = null;
        try {
            reader = StreamFactory.createReader(from);
            StringBuilder buffer = new StringBuilder(CHAR_BUFFER_SIZE);
            copy(reader, buffer);
            return buffer.toString();
        } finally {
            if (reader != null) reader.close();
        }
    }

    /**
     * Read all bytes from specified {@link InputStream} into a byte array.
     *
     * @param from the input to read
     * @throws IOException if an I/O error occurs reading from the stream
     */
    public static byte[] readBytes(InputStream from) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(Math.max(from.available(), BUFFER_SIZE));
        copy(from, output);

        from.close();
        return output.toByteArray();
    }

    /**
     * Writes text data into specified file.
     *
     * @param file the file to read into
     * @param data the text data to write
     * @throws IOException if an I/O error occurs writing to file
     */
    public static void write(File file, String data) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = StreamFactory.createWriter(file);
            writer.write(data);
            writer.flush();
        } finally {
            if (writer != null) writer.close();
        }
    }

    /**
     * Writes byte array into specified file.
     *
     * @param file  the file to read into
     * @param array the bytes to write
     * @throws IOException if an I/O error occurs writing to file
     */
    public static void write(File file, byte[] array) throws IOException {
        OutputStream output = null;
        try {
            output = StreamFactory.createBuffered(new FileOutputStream(file));
            output.write(array);
            output.flush();
        } finally {
            if (output != null) output.close();
        }
    }

    /**
     * Copies all characters from the {@link Reader} to {@link StringBuilder} objects.
     * Does not close or flush either object.
     *
     * @param from the object to read from
     * @param to   the object to write to
     * @return the number of characters copied
     * @throws IOException if an I/O error occurs
     */
    public static long copy(Reader from, StringBuilder to) throws IOException {
        // it is faster than CharBuffer and BufferedReader.readLine
        char[] buff = new char[CHAR_BUFFER_SIZE];
        int read;
        long total = 0;

        while ((read = from.read(buff)) != -1) {
            to.append(buff, 0, read);
            total += read;
        }
        return total;
    }

    /**
     * Copies all characters from the {@link Reader} to {@link Writer} objects.
     * Does not close or flush either object.
     *
     * @param from the object to read from
     * @param to   the object to write to
     * @return the number of characters copied
     * @throws IOException if an I/O error occurs
     */
    public static long copy(Reader from, Writer to) throws IOException {
        char[] buffer = new char[CHAR_BUFFER_SIZE];
        int read;
        long total = 0;

        while ((read = from.read(buffer)) != -1) {
            to.write(buffer, 0, read);
            total += read;
        }
        return total;
    }

    /**
     * Copies all bytes from the {@link InputStream} to {@link OutputStream} objects.
     * Does not close or flush either object.
     *
     * @param from the object to read from
     * @param to   the object to write to
     * @return the number of bytes copied
     * @throws IOException if an I/O error occurs
     */
    public static long copy(InputStream from, OutputStream to) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;
        long total = 0;

        while ((read = from.read(buffer)) != -1) {
            to.write(buffer, 0, read);
            total += read;
        }
        return total;
    }

    /**
     * Copies all bytes data from one file to another
     *
     * @param from the source file to read from
     * @param to   the destination file to write
     * @return the number of bytes copied
     * @throws IOException if an I/O error occurs
     */
    public static long copy(File from, File to) throws IOException {
        InputStream reader = null;
        OutputStream writer = null;

        try {
            reader = StreamFactory.createBuffered(new FileInputStream(from));
            writer = StreamFactory.createBuffered(new FileOutputStream(to));
            return copy(reader, writer);
        } finally {
            if (reader != null) reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    /**
     * Moves a file from one path to another. This method try to rename the file.
     * In case of failure copies a file from one path to another
     * with the subsequent deleting of the one.
     *
     * @param from the source file to read from
     * @param to   the destination file to write
     * @throws IOException if an I/O error occurs
     */
    public static void move(File from, File to) throws IOException {
        boolean success = from.renameTo(to);
        if (!success) {
            // can't rename file. try to copy
            long copied = copy(from, to);
            if (copied > 0) {
                // copy was successful
                if (!from.delete()) {
                    throw new IOException("Unable to delete file: " + to);
                }
            }
        }
    }

    /**
     * Clean and delete directory or file
     *
     * @param directory the dir to delete
     */
    public static void delete(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                delete(file);
            }
        } else {
            directory.delete();
        }
    }

    /**
     * Returns the system-dependent line separator.
     * Used to split lines on text file. On Android, this is {@code "\n"}
     */
    public static String lineSeparator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return System.lineSeparator();
        } else {
            return "\n";
        }
    }

    public static String toString(InputStream input) throws IOException {
        InputStreamReader reader = new InputStreamReader(input);
        StringBuilder builder = new StringBuilder(128);
        copy(reader, builder);

        reader.close();
        return builder.toString();
    }
}
