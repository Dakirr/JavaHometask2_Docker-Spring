package com.dakirr.hometask.storage_service.application.service;

import org.springframework.stereotype.Service;

import com.dakirr.hometask.storage_service.domain.repository.RecordRepository;
import com.dakirr.hometask.storage_service.domain.value_object.Record;


@Service
public class RecordService {
    private RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public int write(Record record) {
        return recordRepository.write(record);
    }

    public Record getById(int id) {
        return recordRepository.getById(id);
    }

    public Record getByHash(String name) {
        return recordRepository.getByHash(name);
    }
}