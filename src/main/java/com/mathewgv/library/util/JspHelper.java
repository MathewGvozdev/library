package com.mathewgv.library.util;

public final class JspHelper {

    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    private JspHelper() {
    }

    public static String getErrorPath() {
        return getPath(JspPath.ERROR_400);
    }

    public static String getPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }
}
