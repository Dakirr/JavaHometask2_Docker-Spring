package com.dakirr.hometask.api_service.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.charset.StandardCharsets;



import com.dakirr.hometask.api_service.domain.value_object.Record;
import com.dakirr.hometask.api_service.domain.value_object.TextFile;


@RestController
@RequestMapping("/api")
public class APIController {
    private final RestTemplate restTemplate;
    @Value("${storage.service.baseurl}") 
    private String storageServiceBaseUrl;
    @Value("${analisys.service.baseurl}") 
    private String analisysServiceBaseUrl;
    


    @Autowired
    public APIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/analise/{id}")
    public ResponseEntity<Record> GetInfo(@PathVariable Integer id) {
        String url = analisysServiceBaseUrl + "/analisys/get/" + id;
        Record R = restTemplate.getForEntity(url, Record.class).getBody();
        return new ResponseEntity<>(R, HttpStatus.OK);
    }

    @PostMapping(value="/load", consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Integer> LoadFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            TextFile file = new TextFile(multipartFile.getOriginalFilename(), multipartFile.getBytes());
            String url = storageServiceBaseUrl + "/storage/post";
            ResponseEntity<Integer> response = restTemplate.postForEntity(url, file, Integer.class);
            return response;
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<TextFile> GetFile(@PathVariable Integer id) {
        String url = storageServiceBaseUrl + "/storage/get/" + id;
        ResponseEntity<TextFile> response = restTemplate.getForEntity(url, TextFile.class);
        return response;
    }
}
