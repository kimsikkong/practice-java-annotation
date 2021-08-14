package com.example.practicejavaannotation.dto.request;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XssPrevent {

}
