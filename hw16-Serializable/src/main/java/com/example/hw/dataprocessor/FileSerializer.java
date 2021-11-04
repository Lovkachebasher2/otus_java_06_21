package com.example.hw.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;
    private final ObjectMapper jacksonMapper;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
        this.jacksonMapper = new ObjectMapper();
    }

    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        //формирует результирующий json и сохраняет его в файл
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
       jacksonMapper.writer().writeValue(fileOutputStream, data);
    }
}
