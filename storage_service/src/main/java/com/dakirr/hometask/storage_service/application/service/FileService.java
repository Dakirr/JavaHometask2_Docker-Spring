package com.dakirr.hometask.storage_service.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dakirr.hometask.storage_service.domain.value_object.File;
import com.dakirr.hometask.storage_service.domain.repository.FileRepository;

@Service
public class FileService {  
    FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String write(File file) {
        return fileRepository.write(file);
    }

    public File get(String path) {
        return fileRepository.get(path);
    }

}
