package com.example.homework.customtestframework;

import com.example.homework.annotation.After;
import com.example.homework.annotation.Before;
import com.example.homework.annotation.Test;
import com.example.homework.exception.ClazzCastException;
import com.example.homework.exception.InvokeException;
import com.example.homework.exception.NotExistException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomTestFramework {
    public static void start(Class<?> clazz) {
        run(clazz);
    }

    private static void run(Class<?> clazz) {
        try {
            runTestMethods(clazz);
        } catch (ClazzCastException | InvokeException | NotExistException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void runTestMethods(Class<?> clazz) throws ClazzCastException, InvokeException, NotExistException, InvocationTargetException, IllegalAccessException {
        Object obj = TestingHelper.getObject(clazz);

        TestingContext testingContext = getTestingContext(obj.getClass().getMethods());
        int amountOfTest = testingContext.getTestMethodList().size();
        int passedTest = 0;
        for (Method method : testingContext.getTestMethodList()) {
            boolean testIsDone = true;
            if (method == null) {
                throw new NotExistException("method is null: " + method.getName());
            }

            if (testingContext.getBeforeMethod() != null) {
                testIsDone = invokeMethod(testingContext.getBeforeMethod(), obj);
            }

            if (testIsDone) {
                testIsDone = invokeMethod(method, obj);
            }

            if (testingContext.getAfterMethod() != null) {
                invokeMethod(testingContext.getAfterMethod(), obj);
            }

            if (testIsDone) {
                passedTest++;
            }
        }
        TestingHelper.printStatistic(amountOfTest, passedTest);
    }

    private static boolean invokeMethod(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj);
        return true;
    }

    private static TestingContext getTestingContext(Method[] methods) {
        return TestingContext.newBuilder()
                .withBeforeMethod(TestingHelper.getMethod(methods, Before.class))
                .withAfterMethod(TestingHelper.getMethod(methods, After.class))
                .withTestMethodList(TestingHelper.getMethods(methods, Test.class))
                .build();
    }
}
