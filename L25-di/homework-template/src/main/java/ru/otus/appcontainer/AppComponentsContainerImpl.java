package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.exception.BasedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        addComponent(configClass);
    }

    private void addComponent(Class<?> clazz) {
        Object instanceConfigClass = getInstance(clazz);
        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(Method::getParameterCount))
                .forEach(method -> {
                    method.setAccessible(true);
                    Class<?>[] parametersType = method.getParameterTypes();
                    Object[] args = new Object[method.getParameterCount()];
                    for (int i = 0; i < parametersType.length; ++i) {
                        args[i] = getAppComponent(parametersType[i]);
                    }
                    Object result  = null;
                    try {
                        result = method.invoke(instanceConfigClass, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                       throw new BasedException(e.getMessage());
                    }
                    appComponentsByName.put(method.getName(), result);
                    appComponents.add(result);
                });
    }



    private Object getInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) getComponentByType(componentClass).orElseThrow(() ->
                new BasedException("can't find component by name: " + componentClass.getName()));
    }

    private Optional<Object> getComponentByType(Class<?> parameterType) {
        return appComponents.stream()
                .filter(component -> parameterType.isAssignableFrom(component.getClass()))
                .findFirst();
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        if (!appComponentsByName.containsKey(componentName)) {
            throw new BasedException("can't find component by name: " + componentName);
        }
        return (C) appComponentsByName.get(componentName);
    }
}
