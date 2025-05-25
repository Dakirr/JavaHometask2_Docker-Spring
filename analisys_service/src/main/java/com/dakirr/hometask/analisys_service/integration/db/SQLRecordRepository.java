package com.dakirr.hometask.analisys_service.integration.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dakirr.hometask.analisys_service.domain.repository.RecordRepository;
import com.dakirr.hometask.analisys_service.domain.value_object.Record;

@Repository
public class SQLRecordRepository implements RecordRepository {
    private final JdbcTemplate jdbcTemplate;
    private String table_name = "analyzes";


    @Autowired
    public SQLRecordRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String createTableSql = "CREATE TABLE IF NOT EXISTS " + table_name + 
                                " (" +
                                "id SERIAL PRIMARY KEY," +
                                "name VARCHAR(255)," +
                                "hash VARCHAR(255) UNIQUE," +
                                "char_num INT," +
                                "word_num INT," +
                                "paragraph_num INT" +
                                ")";
        jdbcTemplate.execute(createTableSql);
    }

    public int write(Record record) {
        String sql = "INSERT INTO " + table_name + "(name, hash, char_num, word_num, paragraph_num) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, record.getName(), record.getHash(), record.getCharNumber(), record.getWordNumber(), record.getParagraphNumber());
        sql = "SELECT id FROM " + table_name + " WHERE hash = ?";
        int id = jdbcTemplate.queryForObject(sql, new Object[]{record.getHash()}, Integer.class);
        return id;
    }

    public Record getById(int id) {
        String sql = "SELECT id, name, hash, char_num, word_num, paragraph_num FROM " + table_name + " WHERE id = ?";
        try {
            Record ret = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Record(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("hash"),
                        rs.getInt("char_num"),
                        rs.getInt("word_num"),
                        rs.getInt("paragraph_num")
                ));
            return ret;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Record getByHash(String hash) {
        String sql = "SELECT id, name, hash, char_num, word_num, paragraph_num FROM " + table_name + " WHERE hash = ?";
        try {
            Record ret = jdbcTemplate.queryForObject(sql, new Object[]{hash}, (rs, rowNum) ->
                new Record(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("hash"),
                        rs.getInt("char_num"),
                        rs.getInt("word_num"),
                        rs.getInt("paragraph_num")
                ));
            return ret;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}
