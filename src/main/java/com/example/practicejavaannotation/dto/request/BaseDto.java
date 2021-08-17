package com.example.practicejavaannotation.dto.request;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public abstract class BaseDto<T> implements EntityConvertable<T> {

    public T createWithPreventXss() {
        preventXss(true);
        return create();
    }

    public void updateWithPreventXss(T t) {
        preventXss(false);
        update(t);
    }

    protected void preventXss(boolean doubleEncode) {
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            preventXss(clazz, doubleEncode);
            clazz = clazz.getSuperclass();
        }
    }

    private void preventXss(Class<?> clazz, boolean doubleEncode) {
        Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(XssPrevent.class) && f.getType().equals(String.class))
                .forEach(f -> {
                    f.setAccessible(true);
                    try {
                        String str = (String) f.get(this);
                        if (str == null) return;
                        if (!doubleEncode) str = decode(str);
                        str = encode(str);
                        f.set(this, str);
                    } catch (IllegalAccessException e) {
                        log.error("Error occurred while accessing the field for the purpose of preventing XSS.", e);
                        throw new IllegalStateException(e);
                    }
                });
    }

    private String encode(String str) {
        return str.replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("'", "&#039;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private String decode(String str) {
        return str.replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&#039;", "'")
                .replace("&lt;", "<")
                .replace("&gt;", ">");
    }

}
