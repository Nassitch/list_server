package com.list.server.domain.enums;

public enum Status {
    ACTIVATED("activated"),
    BANNED("banned");

    public final String path;

    Status(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
