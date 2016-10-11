package ru.euphoria.commons.vk;

/**
 * @since 1.1
 */
public class ApiClient {
    public static final String BASE_URL = "https://api.vk.com/method/";
    public static final double VERSION = 5.53;

    private static volatile ApiClient instance;

    public UserConfig config;

    public ApiClient(UserConfig config) {
        this.config = config;
    }

    public ApiClient init(UserConfig config) {
        if (instance == null) {
            synchronized (ApiClient.class) {
                if (instance == null) {
                    instance = new ApiClient(config);
                }
            }
        }
        return instance;
    }
}
