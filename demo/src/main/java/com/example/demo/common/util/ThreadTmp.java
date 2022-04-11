package com.example.demo.common.util;

public class ThreadTmp{
    private final static ThreadLocal<String> threadLocalForToken = new ThreadLocal<String>();

    public static String getThreadLocalForToken() {
        return threadLocalForToken.get();
    }

    public static void setThreadLocalForToken(String token) {
        threadLocalForToken.set(token);

    }
}
