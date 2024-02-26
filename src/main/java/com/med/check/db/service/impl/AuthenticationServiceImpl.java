package com.med.check.db.service.impl;

import com.med.check.db.config.jwt.JwtService;
import com.med.check.db.dto.request.AuthenticateRequest;
import com.med.check.db.dto.request.ForgetPasswordRequest;
import com.med.check.db.dto.request.RegisterRequest;
import com.med.check.db.dto.response.AuthenticationResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.exception.exceptions.AlreadyExistException;
import com.med.check.db.exception.exceptions.BadRequestException;
import com.med.check.db.exception.exceptions.MessageSendingException;
import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.model.Patient;
import com.med.check.db.model.User;
import com.med.check.db.model.enums.Role;
import com.med.check.db.repository.UserInfoRepository;
import com.med.check.db.repository.UserRepository;
import com.med.check.db.service.AuthenticationService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
    private final JavaMailSender javaMailSender;
    private final Configuration config;

    @Override
    public AuthenticationResponse signUp(RegisterRequest request) {
        Optional<User> userInfo = userInfoRepository.findByEmail(request.email());
        if(userInfo.isPresent()){
            log.error(String.format("Пользователь с адресом электронной почты %s уже существует", request.email()));
            throw new AlreadyExistException(String.format("Пользователь с адресом электронной почты %s уже существует!", request.email()));
        }
        String emailName = request.email().split("@")[0];
        if(request.password().equals(emailName)){
            throw new BadRequestException("Создайте более надежный пароль!");
        }
        User info = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        Patient patient = Patient.builder()
                .firstName(request.name())
                .lastName(request.surName())
                .telNumber(request.telNumber())
                .user(info)
                .build();
        userRepository.save(patient);
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
        User user = userInfoRepository.findByEmail(request.email())
                .orElseThrow(() ->  {
                    log.error(String.format("Пользователь с адресом электронной почты %s не существует", request.email()));
                    return new NotFoundException(String.format("Пользователь с адресом электронной почты %s не существует!", request.email()));
                } );

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            log.error("Пароль не подходит");
            throw new BadRequestException("Пароль не подходит");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
                )
        );
        log.info(String.format("Пользователь %s успешно аутентифицирован", user.getEmail()));
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }


    @Override
    public SimpleResponse sendEmail(ForgetPasswordRequest request) {
        User user = userInfoRepository.findByEmail(request.email())
                .orElseThrow(()->{
                    log.error(String.format("Пользователь с адресом электронной почты %s не зарегистрирован", request.email()));
                    return new NotFoundException(String.format("Пользователь с адресом электронной почты %s не зарегистрирован!", request.email()));
                } );
        String uniqueCode = UUID.randomUUID().toString();
        user.setResetPasswordToken(uniqueCode);


        Map<String, Object> model = new HashMap<>();
        model.put("code", uniqueCode);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Template template = config.getTemplate("mail_template/resetPasswordTemplate.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessageHelper.setTo(request.email());
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Medcheck");
            mimeMessageHelper.setFrom("ilyazovorozali08@gmail.com");
            javaMailSender.send(message);
        } catch (IOException | TemplateException | MessagingException e) {
            log.error("Ошибка при отправке сообщения!");
            throw new MessageSendingException("Ошибка при отправке сообщения!");
        }

        log.info("Сообщение успешно отправлена");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Мы отправили вам по Email ссылку для сброса пароля!")
                .build();
    }

    @Override
    public SimpleResponse confirm(String token) {
        User user = userInfoRepository.findByResetPasswordToken(token)
                .orElseThrow(()->{
                    log.error("Вы ввели неправильный код");
                    return new NotFoundException("Вы ввели неправильный код");
                } );
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(user.getEmail())
                .build();
    }

    @Override
    public AuthenticationResponse resetPassword(String newPassword, String email) {
        User user = userInfoRepository.findByEmail(email)
                .orElseThrow(()->{
                    log.error("Пользователь не существует");
                    return new NotFoundException("Пользователь не существует");
                } );
        user.setResetPasswordToken(null);
        user.setPassword(passwordEncoder.encode(newPassword));
        log.info("Пароль пользователя успешно изменен!");
        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .token(jwtService.generateToken(user))
                .role(Role.USER)
                .build();
    }
}
