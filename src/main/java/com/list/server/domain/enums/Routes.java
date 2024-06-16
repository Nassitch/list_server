package com.list.server.domain.enums;

public enum Routes {
    // Public.
    AUTH("/api/v1/auth/**"),
    PUBLIC("/api/v1/public/**"),
    // Secure.
    CATEGORY("/api/v1/category/**"),
    // Protected.
    USERS_ONLY("/api/v1/demo/user-only"),
    ADMIN_ONLY("/api/v1/demo/admin-only");

    private final String route;

    Routes(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
