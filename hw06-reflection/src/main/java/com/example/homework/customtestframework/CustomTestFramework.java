package com.example.homework.customtestframework;

import com.example.homework.annotation.After;
import com.example.homework.annotation.Before;
import com.example.homework.annotation.Test;
import com.example.homework.exception.ClazzCastException;
import com.example.homework.exception.InvokeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomTestFramework {
    public static void start(Class<?> clazz) {
        run(clazz);
    }

    private static void run(Class<?> clazz) {
        try {
            runTestMethods(clazz);
        } catch (ClazzCastException | InvokeException e) {
            e.printStackTrace();
        }
    }

    private static void runTestMethods(Class<?> clazz) throws ClazzCastException, InvokeException {
        Object obj = getObject(clazz);

        int passedTest = 0;
        int failedTest = 0;

        MethodBuilder methodBuilder = getMethodBuilder(obj.getClass().getMethods());
        for (Method method : methodBuilder.getTestMethodList()) {
            boolean testIsDone = true;
            if (methodBuilder.getBeforeMethod() != null) {
                testIsDone = invokeMethod(methodBuilder.getBeforeMethod(), obj);
            }
            if (method != null && testIsDone) {
                testIsDone = invokeMethod(method, obj);
            }

            if (methodBuilder.getAfterMethod() != null) {
                invokeMethod(methodBuilder.getAfterMethod(), obj);
            }

            if (testIsDone) {
                passedTest++;
            } else {
                failedTest++;
            }
        }
        printStatistic(passedTest, failedTest);
    }

    private static boolean invokeMethod(Method method, Object obj) throws InvokeException {
        boolean testIsDone = true;
        try {
            method.invoke(obj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            testIsDone = false;
            throw new InvokeException("failed invoke " + method.getName(), e);
        }
        return testIsDone;
    }

    private static MethodBuilder getMethodBuilder(Method[] methods) {
        return MethodBuilder.newBuilder()
                .withBeforeMethod(getMethod(methods, Before.class))
                .withAfterMethod(getMethod(methods, After.class))
                .withTestMethodList(getMethods(methods, Test.class))
                .build();
    }

    private static List<Method> getMethods(Method[] methods, Class<? extends Annotation> annotationClass) {
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                testMethods.add(method);
            }
        }
        return testMethods;
    }

    private static Method getMethod(Method[] methods, Class<? extends Annotation> annotationClass) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                return method;
            }
        }
        return null;
    }

    private static void printStatistic(int passed, int failed) {
        System.out.println("passed tests: " + passed);
        System.out.println("failed tests: " + failed);
    }

    private static Object getObject(Class<?> clazz) throws ClazzCastException {
        Object obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new ClazzCastException(e);
        }
        return obj;
    }
}
