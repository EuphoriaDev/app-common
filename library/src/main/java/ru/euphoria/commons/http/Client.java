package ru.euphoria.commons.http;

import android.os.Build;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import ru.euphoria.commons.BuildConfig;
import ru.euphoria.commons.io.EasyStreams;

/**
 * A Simple HTTP library based on {@link HttpURLConnection}
 * to make HTTP requests and access to response.
 * Supports all standard protocols (HTTP, HTTPS, other...).
 *
 * Are requests asynchronous? Yes,
 * use {@link #execute(Request, Request.OnResponseListener)}.
 *
 * Support Android 2.2 (Froyo) and older? Yes.
 * Prior to Android 2.2 (Froyo), {@link HttpURLConnection} class
 * had some frustrating bugs. Works around this by disabling connection pooling
 *
 * Can I get access to the not verified site? Yes.
 * Connection allowed to all hosts.
 *
 * Support caching to disk by url? Yes.
 * use {@link Request.Builder#enableDiskCache(boolean)}
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class Client {
    private static final String TAG = "Client";
    private static final boolean DEBUG = BuildConfig.DEBUG;


    /** Default hostname verifier */
    private static final HostnameVerifier VERIFIER = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true; // Just allow them all
        }
    };

    /** Default trust manager */
    private final static TrustManager[] TRUST_ALL_CERTS = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null; // Not relevant.
                }
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    // Do nothing. Just allow them all.
                }
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    // Do nothing. Just allow them all.
                }
            }
    };

    static {
        // Work around pre-Froyo bugs in HTTP connection reuse
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
        disableCheckVerification();
    }

    private static void disableCheckVerification() {
        System.setProperty("jsse.enableSNIExtension", "false");
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, TRUST_ALL_CERTS, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // allow connection to all hosts
        HttpsURLConnection.setDefaultHostnameVerifier(VERIFIER);
    }

    public static Response execute(Request request) throws IOException {
        HttpURLConnection connection = null;
        try {
            connection = createConnection(request);

            InputStream stream = connection.getInputStream();
            String encoding = connection.getContentEncoding();
            if ("gzip".equalsIgnoreCase(encoding)) {
                stream = EasyStreams.gzip(stream);
            }

            return new Response(connection.getResponseMessage(), connection.getResponseCode(),
                    EasyStreams.readBytes(stream));
        } finally {
            EasyStreams.close(connection);
        }
    }

    /**
     * Creates a new {@link java.net.URLConnection} for specified request.
     */
    private static HttpURLConnection createConnection(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.params == null ? request.url : request.params.join(request.url)).openConnection();
        connection.setReadTimeout(request.readTimeout);
        connection.setConnectTimeout(request.connectTimeout);
        connection.setDoInput(true);
        connection.setUseCaches(request.usesCache);
        connection.setDoOutput(request.isPost());
        connection.setRequestMethod(request.method);
        connection.setRequestProperty(Headers.USER_AGENT, request.userAgent);
        connection.setRequestProperty("Accept-Encoding", "gzip");
        return connection;
    }
}
