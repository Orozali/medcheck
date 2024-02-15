package com.med.check.db.service;

import com.med.check.db.dto.request.AuthenticateRequest;
import com.med.check.db.dto.request.RegisterRequest;
import com.med.check.db.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse signUp(RegisterRequest request);

    AuthenticationResponse signIn(AuthenticateRequest request);
}
