package com.list.server.models.responses;

public class UploadResponse {
    private String file;

    public UploadResponse(String file) {
        this.file = file;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
