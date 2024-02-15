package com.med.check.db.exception.exceptionResponse;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    HttpStatus httpStatus;
    String exceptionClassName;
    String message;
}
