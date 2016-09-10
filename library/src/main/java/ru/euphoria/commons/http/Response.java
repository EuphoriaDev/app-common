package ru.euphoria.commons.http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import ru.euphoria.commons.io.EasyStreams;
import ru.euphoria.commons.util.ArrayUtil;

import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;
import static java.net.HttpURLConnection.HTTP_MULT_CHOICE;
import static java.net.HttpURLConnection.HTTP_SEE_OTHER;

/**
 * An HTTP response. Instances of this class are not immutable: the response body is a one-shot
 * value that may be consumed only once and then closed.
 * All other properties are immutable.
 *
 * After finishing work, you must call the {@link #close()} to release resources.
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class Response implements Closeable {
    /** Numeric status code, 307: Temporary Redirect. */
    public static final int HTTP_TEMP_REDIRECT = 307;
    public static final int HTTP_PERM_REDIRECT = 308;

    private int code;
    private String message;
    private InputStream content;
    private Exception cause;

    /**
     * Internal use only, Creates a new {@link Response}
     *
     * @param message the response message
     * @param code    the response code
     * @param content the content input stream
     */
    Response(String message, int code, InputStream content) {
        this.message = message;
        this.code = code;
        this.content = content;
    }

    /**
     * Returns the response code (200, 404), or -1 if code is unknown.
     *
     * @see #isSuccess()
     * @see #isClientError()
     * @see #isServerError()
     */
    public int code() {
        return code;
    }

    /**
     * Returns the response status message (OK, Not Found),
     * or null if it is unknown.
     */
    public String message() {
        return message;
    }

    /**
     * Returns a string that contains code and response message,
     * separated by space. e.g. 200 OK
     */
    public String statusLine() {
        return code + " " + message;
    }

    /**
     * Returns true if status code is success (2xx), false otherwise
     */
    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }

    /**
     * Returns true if status code is client error (4xx), false otherwise
     */
    public boolean isClientError() {
        return code >= 400 && code < 500;
    }

    /**
     * Returns true if status code is server error (5xx), false otherwise
     */
    public boolean isServerError() {
        return code >= 600 && code < 600;
    }

    /**
     * Returns true if this response redirects to another resource, false otherwise
     */
    public boolean isRedirect() {
        switch (code) {
            case HTTP_PERM_REDIRECT:
            case HTTP_TEMP_REDIRECT:
            case HTTP_MULT_CHOICE:
            case HTTP_MOVED_PERM:
            case HTTP_MOVED_TEMP:
            case HTTP_SEE_OTHER:
                return true;
            default:
                return false;
        }
    }

    /**
     * Return inputStream of this Response
     */
    public InputStream getContent() {
        return content;
    }

    /**
     * Returns information about an exception, if it exists
     */
    public Exception cause() {
        return cause;
    }

    /**
     * Closes this stream, release resources.
     * This operation cannot be reversed, so it should only be
     * called if you are sure there are no further uses for the response.
     */
    @Override
    public void close() {
        EasyStreams.close(content);
    }

    /**
     * Returns true if this content has been released, false otherwise
     */
    public boolean isReleased() {
        return content == null;
    }

    /**
     * This is called by methods that want to throw an exception if the content
     * has already been released
     */
    private void checkReleased() {
        if (isReleased()) {
            throw new IllegalArgumentException("Can't read a released response");
        }
    }

    public byte[] asBytes() {
        checkReleased();
        try {
            return EasyStreams.readBytes(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ArrayUtil.EMPTY_BYTES;
    }

    public String asString() {
        checkReleased();
        try {
            return EasyStreams.read(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public JSONObject asJson() {
        try {
            return new JSONObject(asString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

}
