package com.wetool.push.server;

/**
 * 上下文持有对象（Token令牌）
 * @author zhangjie
 */
public class ContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    public static void set(String token) {
        contextHolder.set(token);
    }

    public static String get() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}