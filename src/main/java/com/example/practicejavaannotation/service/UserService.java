package com.example.practicejavaannotation.service;

import com.example.practicejavaannotation.dto.request.UserDto;
import com.example.practicejavaannotation.entity.User;
import com.example.practicejavaannotation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User create(UserDto userDto) {
        User user = userDto.createWithPreventXss();
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User read(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public User update(Long userId, UserDto userDto) {
        User user = read(userId);
        userDto.updateWithPreventXss(user);
        return userRepository.save(user);
    }

}
