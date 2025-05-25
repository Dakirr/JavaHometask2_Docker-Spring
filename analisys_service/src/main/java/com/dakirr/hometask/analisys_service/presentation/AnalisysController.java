package com.dakirr.hometask.analisys_service.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dakirr.hometask.analisys_service.application.service.RecordService;
import com.dakirr.hometask.analisys_service.domain.service.AnalisysService;
import com.dakirr.hometask.analisys_service.domain.value_object.Record;
import com.dakirr.hometask.analisys_service.domain.value_object.TextFile;


@RestController
@RequestMapping("/analisys")
public class AnalisysController {
    //private FileService fileService;
    private RecordService recordService;
    private AnalisysService analisysService;
    private final RestTemplate restTemplate;
    @Value("${storage.service.baseurl}") 
    private String storageServiceBaseUrl;


    @Autowired
    public AnalisysController(RecordService recordService, AnalisysService analisysService, RestTemplate restTemplate) {
        //this.fileService = fileService;
        this.recordService = recordService;
        this.analisysService = analisysService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Record> GetInfo(@PathVariable Integer id) {
        Record rec = recordService.getById(id);
        if (rec == null) {
            String url = storageServiceBaseUrl + "/storage/get/" + id;
            ResponseEntity<TextFile> response = restTemplate.getForEntity(url, TextFile.class);
            TextFile f = response.getBody();
            if (f == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } 
            rec = analisysService.analyze(f);
            int id1 = recordService.write(rec);
            return new ResponseEntity<>(recordService.getById(id1), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(rec, HttpStatus.OK);
        }
    }
}
