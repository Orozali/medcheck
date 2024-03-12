package com.med.check.db.service.impl;

import com.med.check.db.config.jwt.JwtService;
import com.med.check.db.model.User;
import com.med.check.db.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import static com.med.check.db.model.enums.Role.ADMIN;

@Component
@RequiredArgsConstructor
public class AdminDataLoader implements CommandLineRunner {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void run(String... args) {
        if (userInfoRepository.findByEmail("admin@gmail.com").isEmpty()) {
            User admin = User.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("Admin2024"))
                    .roles(ADMIN)
                    .build();
            userInfoRepository.save(admin);
            System.out.println("ADMIN TOKEN: "+jwtService.generateToken(admin));
        } else {
            System.out.println("ADMIN TOKEN: "+jwtService.generateToken(userInfoRepository.findByEmail("admin@gmail.com").get()));
        }
    }
}