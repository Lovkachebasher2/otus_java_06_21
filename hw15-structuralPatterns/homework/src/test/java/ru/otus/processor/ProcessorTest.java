package ru.otus.processor;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.exception.ProcessorException;
import ru.otus.model.Message;
import ru.otus.processor.homework.ProcessorThrowException;
import ru.otus.processor.homework.TimeProvider;

public class ProcessorTest {
    @Test
    @DisplayName("Тестируем нечетные секунды")
    public void testEvenSecond() {
        final Message message = new Message.Builder(1L).field11("hello").build();

        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);

        //when
        Mockito.when(timeProvider.getSecond()).thenReturn(6);

        Processor processor = new ProcessorThrowException(timeProvider);

        final Throwable throwable = AssertionsForClassTypes.catchThrowable(() -> processor.process(message));

        Assertions.assertThat(throwable).isInstanceOf(ProcessorException.class);
    }
}
