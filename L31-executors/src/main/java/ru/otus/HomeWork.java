package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);
    private String last = "TПоток 2";
    private boolean upper = true;


    private synchronized void action(Map<String, Integer> threadMap) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (last.equals(Thread.currentThread().getName())) {
                    this.wait();
                }

                last = Thread.currentThread().getName();
                int i = 0;
                //number
                i = threadMap.get(last);
                if (i == 10) upper = false;
                if (i==1) upper = true;
                if (upper) {
                    i++;
                }

                if (!upper) {
                    i--;
                }
                logger.info("{}: {}", last, i);
                threadMap.put(last, i);
                sleep();
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        HomeWork homeWork = new HomeWork();

        int countForThreadOne = 0;
        int countForThreadTwo = 0;
        String nameForThreadOne = "Поток 1";
        String nameForThreadTwo = "Поток 2";
        Map<String, Integer> threadMap = new HashMap<>();
        threadMap.put(nameForThreadOne, countForThreadOne);
        threadMap.put(nameForThreadTwo, countForThreadTwo);

        Thread threadOne = new Thread(() -> homeWork.action(threadMap));
        threadOne.setName(nameForThreadOne);
        ;

        Thread threadTwo = new Thread(() -> homeWork.action(threadMap));
        threadTwo.setName(nameForThreadTwo);

        threadOne.start();
        threadTwo.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
