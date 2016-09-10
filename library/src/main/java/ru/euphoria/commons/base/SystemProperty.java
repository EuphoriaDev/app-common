package ru.euphoria.commons.base;

import ru.euphoria.commons.annotation.Nullable;

/**
 * Represents a standard system property {@linkplain System#getProperty(String, String)}.
 *
 * @author Kurt Alfred
 * @author Igor Morozkin
 * @since 1.0
 */
public enum SystemProperty {
    /** Java Runtime Environment version, e.g. "1.7.0" */
    VERSION("java.version"),
    /** Java Runtime Environment vendor, e.g. "Oracle Corporation" */
    VENDOR("java.vendor"),
    /** Java Runtime Environment vendor URL */
    VENDOR_URL("java.vendor.url"),
    /** Java Runtime Environment vendor Bug's URL */
    VENDOR_URL_BUG("java.vendor.url.bug"),
    /** Java installation directory */
    HOME("java.home"),
    /** Name of JIT compiler to use */
    COMPILER("java.compiler"),
    /** Default temporary directory path */
    IO_TEMP_DIR("java.io.tmpdir"),

    /** Java Runtime Environment specification version */
    SPECIFICATION_VERSION("java.specification.version"),
    /** Java Runtime Environment specification vendor */
    SPECIFICATION_VENDOR("java.specification.vendor"),
    /** Java Runtime Environment specification name */
    SPECIFICATION_NAME("java.specification.name"),

    /** Java Runtime Environment name */
    RUNTIME_NAME("java.runtime.name"),
    /** Java SE Runtime Environment version */
    RUNTIME_VERSION("java.runtime.version"),

    /** Java Virtual Machine implementation version */
    VM_VERSION("java.vm.version"),
    /** Java Virtual Machine implementation vendor */
    VM_VENDOR("java.vm.vendor"),
    /** Java Virtual Machine implementation name */
    VM_NAME("java.vm.name"),
    /** Java Virtual Machine implementation info */
    VM_INFO("java.vm.info"),

    /** Java Virtual Machine specification version */
    VM_SPECIFICATION_VERSION("java.vm.specification.version"),
    /** Java Virtual Machine specification vendor */
    VM_SPECIFICATION_VENDOR("java.vm.specification.vendor"),
    /** Java Virtual Machine specification name */
    VM_SPECIFICATION_NAME("java.vm.specification.name"),

    /** Java class format version number */
    CLASS_VERSION("java.class.version"),
    /** Java class path */
    CLASS_PATH("java.class.path"),
    /** Path's list for search JNI libraries */
    LIBRARY_PATH("java.library.path"),

    /** Operating System version, e.g. "2.6.32.9"*/
    OS_VERSION("os.version"),
    /** Operating System name, e.g. "Linux" */
    OS_NAME("os.name"),
    /** Operating System architecture, like "x86" */
    OS_ARCH("os.arch"),

    /** User's account name */
    USER_NAME("user.name"),
    /** User's home directory */
    USER_HOME("user.home"),
    /** User's current working directory */
    USER_DIR("user.dir"),
    /** User's country code, e.g. "US" */
    USER_COUNTRY("user.country"),
    /** User's language code, e.g. "en" */
    USER_LANGUAGE("user.language"),
    /** User's timezone name */
    USER_TIMEZONE("user.timezone"),

    /** File separator ("/" on Unix) */
    FILE_SEPARATOR("file.separator"),
    /** Path separator (":" on Unix) */
    PATH_SEPARATOR("path.separator"),
    /** Line separator ("\n" on Unix) */
    LINE_SEPARATOR("line.separator");

    private final String key;
    SystemProperty(String key) {
        this.key = key;
    }

    /**
     * Returns this key used to get system property
     */
    public String key() {
        return key;
    }

    /**
     * Returns the current value of a system property by this key
     */
    @Nullable
    public String value() {
        return System.getProperty(key);
    }

    @Override
    public String toString() {
        return String.format("%s = %s", key(), value());
    }
}
