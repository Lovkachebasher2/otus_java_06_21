package com.example.hw.dataprocessor;


import com.example.hw.model.Measurement;

import java.io.IOException;
import java.util.List;

public interface Loader {

    List<Measurement> load() throws IOException;
}
