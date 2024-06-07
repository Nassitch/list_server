package com.list.server.domain.enums;

public enum Size {
    SUPER_MARKET("super market"),
    HYPER_MARKET("hyper market");

    public final String path;

    Size(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
