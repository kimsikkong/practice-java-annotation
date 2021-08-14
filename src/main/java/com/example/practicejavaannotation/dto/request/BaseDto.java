package com.example.practicejavaannotation.dto.request;

import java.util.Arrays;

public abstract class BaseDto<T> implements EntityConvertable<T> {

    public T convertWithPreventXss() {
        _preventXss();
        return convert();
    }

    public T convertWithPreventXss(T t) {
        _preventXss();
        return t;
    }

    protected void _preventXss() {
        Arrays.stream(this.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(XssPrevent.class))
                .forEach(f -> {
                    f.setAccessible(true);
                    try {
                        String s = (String) f.get(this);
                        s = escape(s);
                        f.set(this, s);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    private String escape(String str) {
        return str.replace("&", "&amp")
                .replace("<", "&lt")
                .replace(">", "&gt");
    }

}
