package com.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutputMessage {

    private final String from;
    private final String text;
    private final String time;
}
