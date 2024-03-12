package com.med.check.db.service;

import com.med.check.db.dto.request.AuthenticateRequest;
import com.med.check.db.dto.request.ForgetPasswordRequest;
import com.med.check.db.dto.request.RegisterRequest;
import com.med.check.db.dto.response.AuthenticationResponse;
import com.med.check.db.dto.response.SimpleResponse;

import java.util.Locale;

public interface AuthenticationService {
    AuthenticationResponse signUp(RegisterRequest request);

    AuthenticationResponse signIn(AuthenticateRequest request);

    SimpleResponse sendEmail(ForgetPasswordRequest request);

    AuthenticationResponse resetPassword(String newPassword, String email);

    SimpleResponse confirm(String token);
}
