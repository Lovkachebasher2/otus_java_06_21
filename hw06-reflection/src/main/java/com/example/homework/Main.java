package com.example.homework;

import com.example.homework.customtestframework.CustomTestFramework;
import com.example.homework.test.ItemServiceTest;
import com.example.homework.test.UserServiceTest;

public class Main {
    public static void main(String[] args) {
        CustomTestFramework.start(UserServiceTest.class); // должен вывести в консоль отчет об успешмо выполнении
        CustomTestFramework.start(ItemServiceTest.class);//должен вывыести в консоль отчет об не успешном выполнении
    }
}
