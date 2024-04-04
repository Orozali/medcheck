package com.med.check.db.service;

import com.med.check.db.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    String createRefreshToken(String username);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token);
}
