package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record ServiceResponse(
        Long service_id,
        String name
) {
}
