package com.med.check.db.exception.exceptionHandler;

import com.med.check.db.exception.exceptions.*;
import com.med.check.db.exception.exceptionResponse.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler{

    private final MessageSource messageSource;


    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationFailException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse authenticationFail(AuthenticationFailException exception) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse notFound(NotFoundException exception) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .exceptionClassName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse alreadyExist(AlreadyExistException exception) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse methodArgumentNotValid(MethodArgumentNotValidException exception) {
        FieldError fieldError = Objects.requireNonNull(exception.getFieldError());
        String defaultMessage = fieldError.getDefaultMessage();
        assert defaultMessage != null;
        String localizedMessage = messageSource.getMessage(defaultMessage, null, LocaleContextHolder.getLocale());

        System.out.println(exception.getClass().getSimpleName());
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(exception.getClass().getSimpleName())
                .message(localizedMessage)
                .build();
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse badCredential(BadCredentialException exception) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse badCredential(BadRequestException exception) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MessageSendingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse messageSending(MessageSendingException exception){
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
//        return new ResponseEntity<>(body, headers, status);
//    }

}
