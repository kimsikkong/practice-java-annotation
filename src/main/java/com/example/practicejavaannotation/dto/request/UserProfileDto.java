package com.example.practicejavaannotation.dto.request;

import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Value
public class UserProfileDto {

    @NotBlank(groups = OnCreate.class)
    @Length(min = 1, max = 10)
    String nickname;

    @Length(max = 255)
    String description;

    @URL
    String thumbnailUrl;
}
