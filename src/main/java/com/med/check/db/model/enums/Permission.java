package com.med.check.db.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_DELETE("admin:delete"),
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    USER_DELETE("user:delete"),
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    DOCTOR_DELETE("doctor:delete"),
    DOCTOR_READ("doctor:read"),
    DOCTOR_CREATE("doctor:create"),
    DOCTOR_UPDATE("doctor:update")
    ;

    private final String permission;
}
