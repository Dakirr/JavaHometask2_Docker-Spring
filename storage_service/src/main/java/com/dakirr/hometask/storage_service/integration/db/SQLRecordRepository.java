package com.dakirr.hometask.storage_service.integration.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dakirr.hometask.storage_service.domain.repository.RecordRepository;
import com.dakirr.hometask.storage_service.domain.value_object.Record;

@Repository
public class SQLRecordRepository implements RecordRepository {
    private final JdbcTemplate jdbcTemplate;
    private String table_name = "Files";


    @Autowired
    public SQLRecordRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String createTableSql = "CREATE TABLE IF NOT EXISTS " + table_name + 
                                " (" +
                                "id SERIAL PRIMARY KEY," +
                                "name VARCHAR(255)," +
                                "hash VARCHAR(255) UNIQUE," +
                                "path VARCHAR(255)" +
                                ")";
        jdbcTemplate.execute(createTableSql);
    }

    public int write(Record record) {
        String sql = "INSERT INTO " + table_name + "(name, hash, path) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, record.getName(), record.getHash(), record.getPath());
        sql = "SELECT id FROM " + table_name + " WHERE hash = ?";
        int id =  jdbcTemplate.queryForObject(sql, new Object[]{record.getHash()}, Integer.class);
        return id;
    }

    public Record getById(int id) {
        String sql = "SELECT id, name, hash, path FROM " + table_name + " WHERE id = ?";
        try {
            Record ret = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Record(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("hash"),
                        rs.getString("path")
                ));
            return ret;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Record getByHash(String hash) {
        String sql = "SELECT id, name, hash, path FROM " + table_name + " WHERE hash = ?";
        try {
            Record ret = jdbcTemplate.queryForObject(sql, new Object[]{hash}, (rs, rowNum) ->
                new Record(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("hash"),
                        rs.getString("path")
                ));
            return ret;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}
