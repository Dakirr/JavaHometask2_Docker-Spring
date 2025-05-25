package com.dakirr.hometask.storage_service.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dakirr.hometask.storage_service.application.service.FileService;
import com.dakirr.hometask.storage_service.application.service.RecordService;
import com.dakirr.hometask.storage_service.domain.service.HasherService;
import com.dakirr.hometask.storage_service.domain.value_object.File;
import com.dakirr.hometask.storage_service.domain.value_object.Record;

@RestController
@RequestMapping("/storage")
public class StorageController {
    private FileService fileService;
    private RecordService recordService;
    private HasherService hasherService;
    

    @Autowired
    public StorageController(FileService fileService, RecordService recordService, HasherService hasherService) {
        this.fileService = fileService;
        this.recordService = recordService;
        this.hasherService = hasherService;
    }

    @PostMapping("/post")
    public ResponseEntity<Integer> PostFile(@RequestBody File file) {
        try {
            String fileHash = hasherService.hash(file.getContent());
            Record existingRecord = recordService.getByHash(fileHash);

            Integer id;
            if (existingRecord == null) {
                String path = fileService.write(file);
                Record newRecord = new Record(0, file.getName(), fileHash, path);
                id = recordService.write(newRecord);
            } else {
                id = existingRecord.getId();
            }
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<File> GetFile(@PathVariable Integer id) {
        Record record = recordService.getById(id);
        if (record == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            File file = fileService.get(record.getPath());
            return new ResponseEntity<>(file, HttpStatus.OK);
        }
    }

}
