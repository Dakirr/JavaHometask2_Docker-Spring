package com.dakirr.hometask.analisys_service.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dakirr.hometask.analisys_service.domain.repository.FileRepository;
import com.dakirr.hometask.analisys_service.domain.value_object.TextFile;

@Service
public class FileService {  
    FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String write(TextFile file) {
        return fileRepository.write(file);
    }

    public TextFile get(String path) {
        return fileRepository.get(path);
    }

}
