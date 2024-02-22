package com.med.check.db.api;

import com.med.check.db.dto.request.AuthenticateRequest;
import com.med.check.db.dto.request.ForgetPasswordRequest;
import com.med.check.db.dto.request.RegisterRequest;
import com.med.check.db.dto.request.ResetPasswordRequest;
import com.med.check.db.dto.response.AuthenticationResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.exception.exceptionResponse.ExceptionResponse;
import com.med.check.db.exception.exceptions.BadCredentialException;
import com.med.check.db.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Sign up method", description = "This method takes users information and registers!")
    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@RequestBody @Valid RegisterRequest request){
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Sign in method", description = "This method sign in method!")
    @PostMapping("/sign-in")
    public AuthenticationResponse signIn(@RequestBody @Valid AuthenticateRequest request){
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Forget password method", description = "This method is for sending user url to change his password!")
    @PostMapping("/forget-password")
    public SimpleResponse forgetPassword(@RequestBody @Valid ForgetPasswordRequest request){
        return authenticationService.sendEmail(request);
    }

    @Operation(summary = "Confirm method", description = "This method is for confirming")
    @PostMapping("/confirm")
    public SimpleResponse resetPassword(@RequestParam String token){
        return authenticationService.confirm(token);
    }

    @Operation(summary = "Reset password method", description = "This method is for reseting password")
    @PostMapping("/reset-password")
    public AuthenticationResponse resetPassword(@RequestParam String email, @RequestBody @Valid ResetPasswordRequest request){
        return authenticationService.resetPassword(request.newPassword(), email);
    }
}