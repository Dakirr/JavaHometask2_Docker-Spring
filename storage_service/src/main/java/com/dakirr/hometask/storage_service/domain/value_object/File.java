package com.dakirr.hometask.storage_service.domain.value_object;

public class File {
    private String name;
    private byte[] content;

    public File(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }
}
