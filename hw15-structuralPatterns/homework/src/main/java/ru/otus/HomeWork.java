package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinterConsole;
import ru.otus.model.Message;
import ru.otus.processor.homework.ProcessorSwap;
import ru.otus.processor.homework.ProcessorThrowException;
import ru.otus.processor.homework.TimeProvider;
import ru.otus.processor.homework.TimeProviderImpl;

import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяьться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {
        TimeProvider timeProvider = new TimeProviderImpl();
      var processorList = List.of(
              new ProcessorSwap(),
              new ProcessorThrowException(timeProvider)
      );

      var complexProcessor = new ComplexProcessor(processorList, e -> {});
      var listenerPrinter = new ListenerPrinterConsole();
      complexProcessor.addListener(listenerPrinter);

      var message = new Message.Builder(1L)
              .field10("F10")
              .field11("F11")
              .build();

      var result = complexProcessor.handle(message);
        System.out.println("result: " + result);

        complexProcessor.removeListener(listenerPrinter);
    }
}
