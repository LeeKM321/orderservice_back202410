package com.playdata.orderservice.user.service;

import com.playdata.orderservice.user.dto.UserSaveReqDto;
import com.playdata.orderservice.user.entity.User;
import com.playdata.orderservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User userCreate(@Valid UserSaveReqDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다!");
        }
        User saved = userRepository.save(dto.toEntity(encoder));
        log.info("saved: {}", saved);
        return saved;
    }
}
















