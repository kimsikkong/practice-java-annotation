package com.example.practicejavaannotation.dto.request;

import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class UserDto {

    @NotBlank(groups = OnCreate.class)
    @Length(min = 1, max = 10)
    String name;

    @NotBlank(groups = OnCreate.class)
    @Min(1)
    @Max(100)
    Integer age;

    @NotBlank(groups = OnCreate.class)
    @Length(max = 255)
    String address;

    @NotBlank(groups = OnCreate.class)
    @Pattern(regexp = "[\\d]{3}-[\\d]{4}-[\\d]{4}")
    String phoneNumber;

}
