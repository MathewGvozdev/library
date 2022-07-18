package com.mathewgv.library.util;

public final class JspHelper {

    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    private JspHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getErrorPath() {
        return getPath("error");
    }

    public static String getPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }
}
