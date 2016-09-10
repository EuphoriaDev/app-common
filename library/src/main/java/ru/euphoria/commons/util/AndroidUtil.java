package ru.euphoria.commons.util;

import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import ru.euphoria.commons.annotation.Nullable;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.DOWNLOAD_SERVICE;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Provides static utils methods for Android OS.
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class AndroidUtil {

    /**
     * Show the input method's soft input
     *
     * @param context the context to get input method manager
     * @param view    the view to get token
     */
    public static void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null) {
            imm.showSoftInputFromInputMethod(view.getWindowToken(), InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * Returns status bar height in pixels.
     *
     * @param context the context to get Resources
     */
    public static int getStatusBarHeight(Context context) {
        int resource = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resource > 0) {
            return context.getResources().getDimensionPixelSize(resource);
        }
        return 0;
    }

    /**
     * Converts device specific pixels to density independent pixels.
     *
     * @param context the context to get resources and device specific display metrics
     * @param px      the value in px (pixels) unit. Which we need to convert into db
     */
    public static int dp(final Context context, int px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    /**
     * Converts dp unit to equivalent pixels, depending on device density.
     *
     * @param context the context to get resources and device specific display metrics
     * @param dp      the value in dp (density independent pixels) unit
     */
    public static int px(final Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * Converts device specific pixels to scaled pixels.
     *
     * @param context the context to get resources and device specific display metrics
     * @param px      the value in px (pixels) unit
     */
    public static int sp(final Context context, int px) {
        return (int) (px * context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * Return the handle to a system-level service by name.
     *
     * @param context the context used to get service of app
     * @param service the name of service
     * @param <T>     the generic type of service
     *
     * @see Context#getSystemService(String)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getService(Context context, String service) {
        return (T) context.getSystemService(service);
    }

    /**
     * Obtains an instance of the system {@link Vibrator}
     *
     * @param context the context used to get service of app
     */
    public static Vibrator getVibrator(Context context) {
        return getService(context, VIBRATOR_SERVICE);
    }

    /**
     * Obtains an instance of the system {@link ActivityManager}
     *
     * @param context the context used to get service of app
     */
    public static ActivityManager getActivityManager(Context context) {
        return getService(context, ACTIVITY_SERVICE);
    }

    /**
     * Obtains an instance of the system {@link LayoutInflater}
     *
     * @param context the context used to get service of app
     */
    public static LayoutInflater getLayoutInflater(Context context) {
        return getService(context, LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Obtains an instance of the system {@link DownloadManager}
     *
     * @param context the context used to get service of app
     */
    public static DownloadManager getDownloadManager(Context context) {
        return getService(context, DOWNLOAD_SERVICE);
    }

    /**
     * Returns true if this device is low-memory (512 MB or lower).
     *
     * @param context the context used to get activity manager
     */
    public boolean isLowMemory(Context context) {
        if (hasKitKat()) {
            // method only for KitKat+
            return getActivityManager(context).isLowRamDevice();
        }
        // check for total memory < 512MB
        return Runtime.getRuntime().totalMemory() / 1024 / 1024 > 512;
    }

    /**
     * Copies specified text to clipboard.
     *
     * @param context the context used to get service of app
     * @param text    the text to copy, may be null
     */
    public static void copyText(Context context, @Nullable String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (hasHoneycomb()) {
            android.content.ClipboardManager clipboard = getService(context, CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
        } else {
            android.text.ClipboardManager clipboard = getService(context, CLIPBOARD_SERVICE);
            clipboard.setText(text);
        }
    }

    /**
     * Indicates whether network connectivity exists and it is possible
     * NOTE: Need add uses-permission for.ACCESS_NETWORK_STATE.
     *
     * @param c the context used to get connection manager
     */
    public static boolean hasConnection(Context c) {
        if (c == null) {
            return false;
        }
        ConnectivityManager cm = getService(c, CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isAvailable() &&
                cm.getActiveNetworkInfo().isConnected());

    }

    /**
     * Check whether the given feature name is one of the available
     *
     * @param context the context used to get package manager
     * @param feature the feature name
     */
    public static boolean hasFeature(Context context, String feature) {
        return context.getPackageManager().hasSystemFeature(feature);
    }

    /**
     * Check if this device has a camera
     *
     * @param context the context used to get package manager
     */
    public static boolean hasCamera(Context context) {
        return hasFeature(context, PackageManager.FEATURE_CAMERA);
    }

    /**
     * Returns true if android version is Cupcake (1.5) or upper.
     * Hmm... Stop, returns always true because it's first version :D.
     */
    @Deprecated
    public static boolean hasBase() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE;
    }

    /**
     * Returns true if android version is Cupcake (1.5) or upper,
     * false otherwise
     */
    @Deprecated
    public static boolean hasCupcake() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE;
    }

    /**
     * Returns true if android version is Donut (1.6) or upper,
     * false otherwise
     */
    @Deprecated
    public static boolean hasDonut() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT;
    }

    /**
     * Returns true if android version is Eclair (2.0) or upper,
     * false otherwise
     */
    @Deprecated
    public static boolean hasEclair() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR;
    }

    /**
     * Returns true if android version is Froyo (2.2) or upper,
     * false otherwise
     */
    @Deprecated
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * Returns true if android version is Gingerbread (2.3) or upper,
     * false otherwise
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * Returns true if android version is Honeycomb (3.0) or upper,
     * false otherwise
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * Returns true if android version is Ice Cream Sandwich (4.0) or upper,
     * false otherwise
     */
    public static boolean hasIcs() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * Returns true if android version is Jelly Bean (4.1) or upper,
     * false otherwise
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * Returns true if android version is Kit Kat (4.4) or upper,
     * false otherwise
     */
    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * Returns true if android version is Lollipop (5.0) or upper,
     * false otherwise
     */
    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Returns true if android version is Marshmallow (6.0) or upper,
     * false otherwise
     */
    public static boolean hasMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


}
