package com.example.practicejavaannotation.dto.request;

public interface EntityConvertable<T> {

    T convert();

    T convert(T t);
}
