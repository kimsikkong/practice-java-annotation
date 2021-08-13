package com.example.practicejavaannotation.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDtoTest {

    private final Logger log = LoggerFactory.getLogger(UserDtoTest.class);

    @Autowired
    ValidatorFactory factory;

    @Test
    void testValidate() {
        UserDto userDto = new UserDto(
                "name",
                30,
                "seoul",
                "010-1234-5678"
        );
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserDto>> result = validator.validate(userDto);
        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void testValidateInvalidDto() {
        UserDto invalidDto = new UserDto(
                null,
                101,
                "",
                "01012345678"
        );
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserDto>> result = validator.validate(invalidDto, OnCreate.class);
        Assertions.assertThat(result.size()).isNotEqualTo(0);
        result.forEach(violation -> log.info("path : {}, message : {}, value : {}", violation.getPropertyPath(), violation.getMessage(), violation.getInvalidValue()));
    }

}