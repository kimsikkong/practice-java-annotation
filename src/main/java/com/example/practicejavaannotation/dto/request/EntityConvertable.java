package com.example.practicejavaannotation.dto.request;

public interface EntityConvertable<T> {

    T create();

    void update(T t);
}
