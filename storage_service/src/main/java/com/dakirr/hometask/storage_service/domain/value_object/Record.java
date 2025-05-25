package com.dakirr.hometask.storage_service.domain.value_object;

public class Record {
    private int id;
    private String name;
    private String hash;
    private String path;
    
    public Record(int id, String name, String hash, String path) {
        this.id = id;
        this.name = name;
        this.hash = hash;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }

    public String getPath() {
        return path;
    }

};
