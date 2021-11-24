package ru.otus.processor.homework;

import ru.otus.exception.ProcessorException;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorThrowException implements Processor {

    private final TimeProvider timeProvider;

    public ProcessorThrowException(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }


    @Override
    public Message process(Message message) {
        if (timeProvider.getSecond() % 2 == 0) {
            throw new ProcessorException("ooops second second");
        }
        return message;
    }
}
