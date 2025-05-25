package com.dakirr.hometask.storage_service.domain.repository;

import org.springframework.stereotype.Repository;

import com.dakirr.hometask.storage_service.domain.value_object.File;



@Repository
public interface FileRepository {
    public File get(String filename) throws RuntimeException;
    public String write(File file) throws RuntimeException;
}
