package ru.euphoria.commons.time;

/**
 * Time Constants.
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class Times {
    public static final long SEC   = 1_000;
    public static final long MIN   = SEC * 60;
    public static final long HOUR  = MIN * 60;
    public static final long DAY   = HOUR * 24;
    public static final long WEEK  = DAY * 7;
    public static final long MONTH = DAY * 30;
    public static final long YEAR  = MONTH * 12;

    public static final long SEC_PER_MIN   = MIN / SEC;
    public static final long SEC_PER_HOUR  = HOUR / SEC;
    public static final long SEC_PER_DAY   = DAY / SEC;
    public static final long SEC_PER_WEEK  = WEEK / SEC;
    public static final long SEC_PER_MONTH = MONTH / SEC;
    public static final long SEC_PER_YEAR  = YEAR / SEC;

    public static final long MIN_PER_HOUR  = HOUR / MIN;
    public static final long MIN_PER_DAY   = DAY / MIN;
    public static final long MIN_PER_WEEK  = WEEK / MIN;
    public static final long MIN_PER_MONTH = MONTH / MIN;
    public static final long MIN_PER_YEAR  = YEAR / MIN;

    public static final long HOUR_PER_DAY   = DAY / HOUR;
    public static final long HOUR_PER_WEEK  = WEEK / HOUR;
    public static final long HOUR_PER_MONTH = MONTH / HOUR;
    public static final long HOUR_PER_YEAR  = YEAR / HOUR;

    public static final long DAY_PER_WEEK  = WEEK / DAY;
    public static final long DAY_PER_MONTH = MONTH / DAY;
    public static final long DAY_PER_YEAR  = YEAR / DAY;

    public static final long WEEK_PER_MONTH = MONTH / WEEK;
    public static final long WEEK_PER_YEAR  = YEAR / WEEK;

    public static final long MONTH_PER_YEAR = YEAR / MONTH;

}
