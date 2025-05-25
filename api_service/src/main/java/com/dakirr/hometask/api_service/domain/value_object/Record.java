package com.dakirr.hometask.api_service.domain.value_object;

public class Record {
    private int id;
    private String name;
    private String hash;
    private int char_number;
    private int word_number;
    private int paragraph_number;
    
    public Record(int id, String name, String hash, int char_number, int word_number, int paragraphs_number) {
        this.id = id;
        this.name = name;
        this.hash = hash;
        this.char_number = char_number;
        this.word_number = word_number;
        this.paragraph_number = paragraphs_number;
    }

    public int getCharNumber() {
        return char_number;
    }

    public int getWordNumber() {
        return word_number;
    }

    public int getParagraphNumber() {
        return paragraph_number;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setCharNumber(int char_number) {
        this.char_number = char_number;
    }

    public void setWordNumber(int word_number) {
        this.word_number = word_number;
    }

    public void setParagraphNumber(int paragraph_number) {
        this.paragraph_number = paragraph_number;
    }
    
};
