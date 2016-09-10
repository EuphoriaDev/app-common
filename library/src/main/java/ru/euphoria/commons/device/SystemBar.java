package ru.euphoria.commons.device;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import ru.euphoria.commons.util.AndroidUtil;

/**
 * Class to manage status and navigation bar tint effects when using KitKat
 * translucent system UI modes and Lollipop.
 *
 * Powered by SystemBarTintManager
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class SystemBar {
    private static int STATUS_BAR_ID = generateId();
    private static int STATUS_BAR_HEIGHT = -1;

    /**
     * Sets specified color to system status bar on KitKat or Lollipop
     *
     * @param screen the activity of status bar
     * @param color  the background color to apply
     */
    public static void setStatusBarColor(Activity screen, int color) {
        if (screen == null) {
            return;
        }

        Window window = screen.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            View statusBar = findStatusBarView(screen);
            if (statusBar == null) {
                statusBar = initKitKatStatusBar(screen);
            }
            statusBar.setBackgroundColor(color);
        }
    }

    /**
     * Returns the color of system status bar on KitKat or Lollipop
     *
     * @param screen the activity of status bar
     */
    public static int getStatusBarColor(Activity screen) {
        if (screen == null) {
            throw new NullPointerException("Screen is null, can't get status bar color.");
        }

        Window window = screen.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return window.getStatusBarColor();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View statusBar = findStatusBarView(screen);
            if (statusBar != null) {
                Drawable background = statusBar.getBackground();
                if (background != null && background instanceof ColorDrawable) {
                    return ((ColorDrawable) background).getColor();
                }
            }
        }

        // Default status bar color
        return Color.BLACK;
    }

    private static View findStatusBarView(Activity screen) {
        return screen.findViewById(STATUS_BAR_ID);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static View initKitKatStatusBar(Activity context) {
        if (STATUS_BAR_HEIGHT == -1) {
            STATUS_BAR_HEIGHT = AndroidUtil.getStatusBarHeight(context);
        }

        View decorView = context.getWindow().getDecorView();
        View statusBar = new View(context);
        statusBar.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, STATUS_BAR_HEIGHT));
        ((ViewGroup) decorView).addView(statusBar);

        return statusBar;
    }

    private static int generateId() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                ? View.generateViewId() : 0;
    }
}
