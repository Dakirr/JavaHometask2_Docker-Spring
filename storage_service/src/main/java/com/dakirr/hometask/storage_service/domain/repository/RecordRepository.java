package com.dakirr.hometask.storage_service.domain.repository;

import org.springframework.stereotype.Repository;

import com.dakirr.hometask.storage_service.domain.value_object.Record;


@Repository
public interface RecordRepository {
    public Record getById(int id) throws RuntimeException;
    public Record getByHash(String name) throws RuntimeException;
    public int write(Record record) throws RuntimeException;
}
