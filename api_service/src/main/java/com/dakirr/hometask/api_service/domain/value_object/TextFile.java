package com.dakirr.hometask.api_service.domain.value_object;

public class TextFile {
    private String name;
    private byte[] content;

    public TextFile(String name, byte[] content) {
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
