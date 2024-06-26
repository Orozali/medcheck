package com.med.check.db.dto.response;

import com.med.check.db.model.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String email,
        Role roles,
        String accessToken,
        String refreshToken
) {
}
