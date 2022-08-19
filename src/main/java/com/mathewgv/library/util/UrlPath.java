package com.mathewgv.library.util;

public final class UrlPath {

    public static final String HOME = "/home";
    public static final String LOGIN = "/home?cmd=login";
    public static final String FIND_ALL_ORDERS = "/home?cmd=find_all_orders&page=1&status=all";
    public static final String FIND_USER_INFO = "/home?cmd=find_user_info&userId=";
    public static final String IMAGES = "/images";

    private UrlPath() {
    }
}
