package com.med.check.db.service.impl;

import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.model.RefreshToken;
import com.med.check.db.repository.RefreshTokenRepository;
import com.med.check.db.repository.UserInfoRepository;
import com.med.check.db.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserInfoRepository userInfoRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public String createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .userInfo(userInfoRepository.findByEmail(username)
                        .orElseThrow(()->new NotFoundException("Username not found!")))
                .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}
