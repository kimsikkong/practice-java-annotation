package com.example.practicejavaannotation.controller;

import com.example.practicejavaannotation.dto.request.OnCreate;
import com.example.practicejavaannotation.dto.request.OnUpdate;
import com.example.practicejavaannotation.dto.request.UserDto;
import com.example.practicejavaannotation.entity.User;
import com.example.practicejavaannotation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public Mono<User> get(@PathVariable Long userId) {
        return Mono.fromCallable(() -> userService.read(userId));
    }

    @PostMapping
    public Mono<User> create(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        return Mono.fromCallable(() -> userService.create(userDto));
    }

    @PatchMapping("/{userId}")
    public Mono<User> update(@PathVariable Long userId, @Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        return Mono.fromCallable(() -> userService.update(userId, userDto));
    }


}
