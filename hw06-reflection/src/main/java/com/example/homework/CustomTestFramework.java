package com.example.homework;

import com.example.homework.annotation.After;
import com.example.homework.annotation.Before;
import com.example.homework.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomTestFramework {
    private static Object obj;

    public static void start(Class<?> clazz) {
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            runTestMethods(obj);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void invokeMethod(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj);
        System.out.println("method " + method.getName() + " in class " + obj.getClass().getSimpleName() + " have been invoke");
    }

    private static void runTestMethods(Object obj) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int passedTest = 0;
        int failedTest = 0;
        Method methodBefore = null;
        Method methodAfter = null;
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                methodBefore = method;
                break;
            }
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(After.class)) {
                methodAfter = method;
                break;
            }
        }
        for (Method method : methods) {
            boolean testIsDone = true;
            if (method.isAnnotationPresent(Test.class)) {
                if (methodBefore != null) {
                    try {
                        invokeMethod(methodBefore, obj);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                        testIsDone = false;
                    }
                }
                try {
                    invokeMethod(method, obj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    testIsDone = false;
                }
                if (methodAfter != null) {
                    try {
                        invokeMethod(methodAfter, obj);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                        testIsDone = false;
                    }
                }
                if (testIsDone) {
                    System.out.println("\n" + "test is name: " + method.getName() + " passed" + " \n");
                    passedTest++;
                } else {
                    System.out.println("test is name: " + method.getName() + " failed");
                    failedTest++;
                }
            }
        }
        System.out.println(obj.getClass().getSimpleName());
        System.out.println("Passed tests is: " + passedTest);
        System.out.println("Failed tests is: " + failedTest);
    }
}
