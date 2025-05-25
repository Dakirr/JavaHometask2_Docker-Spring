package com.dakirr.hometask.analisys_service.domain.repository;

import org.springframework.stereotype.Repository;

import com.dakirr.hometask.analisys_service.domain.value_object.TextFile;



@Repository
public interface FileRepository {
    public TextFile get(String filename) throws RuntimeException;
    public String write(TextFile file) throws RuntimeException;
}
