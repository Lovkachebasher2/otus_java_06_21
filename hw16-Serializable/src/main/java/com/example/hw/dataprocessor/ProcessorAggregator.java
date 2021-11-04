package com.example.hw.dataprocessor;

import com.example.hw.model.Measurement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value

        Map<String, Double> process = new HashMap<>();
        data.forEach(
                measurement -> {
                    if (process.containsKey(measurement.getName())) {
                        Double value = process.get(measurement.getName()) + measurement.getValue();
                        process.put(measurement.getName(), value);
                    } else {
                        process.put(measurement.getName(), measurement.getValue());
                    }
                }
        );

        return process.entrySet().stream().sorted(
                Map.Entry.comparingByValue()
        ).collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
