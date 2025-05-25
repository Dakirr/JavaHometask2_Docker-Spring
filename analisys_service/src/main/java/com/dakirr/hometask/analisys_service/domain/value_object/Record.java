package com.dakirr.hometask.analisys_service.domain.value_object;

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
};
