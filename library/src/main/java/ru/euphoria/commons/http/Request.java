package ru.euphoria.commons.http;

/**
 * Class for make and config the HTTP Request
 * @author Igor Morozkin
 * @since 1.0
 */
public class Request {
    public static final String MOBILE_USER_AGENT = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
    public static final int READ_TIMEOUT = 60_000;
    public static final int CONNECT_TIMEOUT = 60_000;

    public static final String POST = "POST";
    public static final String GET = "GET";

    public final String url;
    public final String method;
    public final String userAgent;
    public final int connectTimeout;
    public final int readTimeout;
    public final boolean usesCache;

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.userAgent = builder.userAgent;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.usesCache = builder.usesCache;
    }

    /**
     * Returns a new {@link Builder} to make Request
     */
    public static Builder builder(String url) {
        return new Builder(url);
    }

    /**
     * Returns true if method is POST
     */
    public boolean isPost() {
        return method.equalsIgnoreCase(POST);
    }

    /**
     * Returns true if method is GET
     */
    public boolean isGet() {
        return method.equalsIgnoreCase(GET);
    }

    /**
     * Creates a new Request with specified url and GET method
     *
     * @param url the url address to connect
     */
    public static Request get(String url) {
        return builder(url)
                .method(GET)
                .build();
    }

    /**
     * Creates a new Request with specified url and POST method
     *
     * @param url the url address to connect
     */
    public static Request post(String url) {
        return builder(url)
                .method(POST)
                .build();
    }

    /**
     * Builder class for {@link Request}.
     * Provides a convenient way to set various fields to {@link Request}
     */
    public static class Builder {
        private String url;
        private String method;
        private String userAgent;
        private int connectTimeout;
        private int readTimeout;
        private boolean usesCache;

        public Builder(String url) {
            url(url);
            method(GET);
            userAgent(MOBILE_USER_AGENT);
            timeout(READ_TIMEOUT, CONNECT_TIMEOUT);
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder timeout(int read, int connect) {
            this.readTimeout = read;
            this.connectTimeout = connect;
            return this;
        }

        public Builder usesCache(boolean usesCache) {
            this.usesCache = usesCache;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

}
