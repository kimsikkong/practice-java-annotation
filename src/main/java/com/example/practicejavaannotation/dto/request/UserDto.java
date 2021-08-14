package com.example.practicejavaannotation.dto.request;

import com.example.practicejavaannotation.entity.User;
import lombok.Getter;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.Instant;

@Getter
public class UserDto extends BaseDto<User> {

    @NotBlank(groups = OnCreate.class)
    @Length(min = 1, max = 10)
    @XssPrevent
    private final String name;

    @NotNull(groups = OnCreate.class)
    @Min(1)
    @Max(100)
    private final Integer age;

    @NotBlank(groups = OnCreate.class)
    @Length(max = 255)
    private final String address;

    @NotBlank(groups = OnCreate.class)
    @Pattern(regexp = "[\\d]{3}-[\\d]{4}-[\\d]{4}")
    private final String phoneNumber;

    public UserDto(String name, Integer age, String address, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public User convert() {
        return new User(name, age, address, phoneNumber, Instant.now().getEpochSecond());
    }

    @Override
    public User convert(User user) {
        if (name != null) user.setName(name);
        if (age != null) user.setAge(age);
        if (address != null) user.setAddress(address);
        if (phoneNumber != null) user.setPhoneNumber(phoneNumber);
        return user;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
