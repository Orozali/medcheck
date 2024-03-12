package com.med.check.db.dto.response;

import com.med.check.db.model.enums.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record AuthenticationResponse(
        String email,
        Role roles,
        String token
) {
}
