package com.example.hw.dataprocessor;

import com.example.hw.model.Measurement;
import com.example.hw.model.MeasurementDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileLoader implements Loader {
    private final ObjectMapper jacksonMapper;
    private final String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
        this.jacksonMapper = new ObjectMapper();
    }

    @Override
    public List<Measurement> load() throws IOException {
        List<Measurement> measurementList = new ArrayList<>();
        TypeFactory typeFactory = jacksonMapper.getTypeFactory();
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        List<MeasurementDto> measurementDtoList = jacksonMapper.readValue(is, typeFactory.constructCollectionType(List.class, MeasurementDto.class));
        measurementDtoList.forEach(
                measurementDto -> {
                    measurementList.add(new Measurement(measurementDto.getName(), measurementDto.getValue()));
                }
        );
        return measurementList;
    }
}
