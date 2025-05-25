package com.dakirr.hometask.analisys_service.domain.service;

import java.nio.charset.StandardCharsets; 

import org.springframework.stereotype.Service; 

import com.dakirr.hometask.analisys_service.domain.value_object.Record;
import com.dakirr.hometask.analisys_service.domain.value_object.TextFile;

@Service
public class AnalisysService {
    private HasherService hasherService = new HasherService();

    public AnalisysService() {};

    public Record analyze(TextFile f) {
        try {            
            String text = new String(f.getContent(), StandardCharsets.UTF_8);
            int char_num = text.length();
            int words_num = text.split(" |\\n").length;
            int paragraph_num = text.split("\\n").length;
            return new Record(0, f.getName(), hasherService.hash(f.getContent()), char_num, words_num, paragraph_num);
        } catch (Exception e) {
            return null;
        }
    }

}
