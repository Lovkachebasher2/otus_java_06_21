package com.example.homework.customtestframework;

import com.example.homework.exception.ClazzCastException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingHelper {
    public static List<Method> getMethods(Method[] methods, Class<? extends Annotation> annotationClass) {
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                testMethods.add(method);
            }
        }
        return testMethods;
    }

    public static Method getMethod(Method[] methods, Class<? extends Annotation> annotationClass) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                return method;
            }
        }
        return null;
    }

    public static void printStatistic(int amountOfTest, int passed) {
        System.out.println("amount of tests: " + amountOfTest);
        System.out.println("passed tests: " + passed);
        System.out.println("failed tests: " + (amountOfTest - passed));
    }

    public static Object getObject(Class<?> clazz) throws ClazzCastException {
        Object obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new ClazzCastException(e);
        }
        return obj;
    }
}
