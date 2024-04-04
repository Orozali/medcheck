package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record JwtResponseDTO (
        String accessToken,
        String refreshToken
){
}
