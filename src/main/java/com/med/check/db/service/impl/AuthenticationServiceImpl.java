package com.med.check.db.service.impl;

import com.med.check.db.config.jwt.JwtService;
import com.med.check.db.dto.request.AuthenticateRequest;
import com.med.check.db.dto.request.RegisterRequest;
import com.med.check.db.dto.response.AuthenticationResponse;
import com.med.check.db.exception.exceptions.AlreadyExistException;
import com.med.check.db.exception.exceptions.BadRequestException;
import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.model.User;
import com.med.check.db.model.UserInfo;
import com.med.check.db.model.enums.Role;
import com.med.check.db.repository.UserInfoRepository;
import com.med.check.db.repository.UserRepository;
import com.med.check.db.service.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signUp(RegisterRequest request) {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(request.email());
        if(userInfo.isPresent()){
            log.error(String.format("Пользователь с адресом электронной почты %s уже существует", request.email()));
            throw new AlreadyExistException(String.format("Пользователь с адресом электронной почты %s уже существует!", request.email()));
        }
        String emailName = request.email().split("@")[0];
        if(request.password().equals(emailName)){
            throw new BadRequestException("Создайте более надежный пароль!");
        }
        UserInfo info = UserInfo.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        User user = User.builder()
                .firstName(request.name())
                .lastName(request.surName())
                .telNumber(request.telNumber())
                .userInfo(info)
                .build();
        userRepository.save(user);
        log.info(String.format("Пользователь %s успешно сохранен!", info.getEmail()));
        String token = jwtService.generateToken(info);
        return AuthenticationResponse.builder()
                .email(info.getEmail())
                .role(info.getRole())
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(AuthenticateRequest request) {
        UserInfo userInfo = userInfoRepository.findByEmail(request.email())
                .orElseThrow(() ->  {
                    log.error(String.format("Пользователь с адресом электронной почты %s не существует", request.email()));
                    return new NotFoundException(String.format("Пользователь с адресом электронной почты %s не существует!", request.email()));
                } );

        if(!passwordEncoder.matches(request.password(), userInfo.getPassword())){
            log.error("Пароль не подходит");
            throw new BadRequestException("Пароль не подходит");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
                )
        );
        log.info(String.format("Пользователь %s успешно аутентифицирован", userInfo.getEmail()));
        String token = jwtService.generateToken(userInfo);
        return AuthenticationResponse.builder()
                .token(token)
                .role(userInfo.getRole())
                .email(userInfo.getEmail())
                .build();
    }
}
