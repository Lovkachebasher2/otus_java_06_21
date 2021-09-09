package example.homework.proxy.aop;

import example.homework.proxy.utill.Log;
import example.homework.proxy.utill.LogClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.stream.Collectors;

public class Ioc {

    private Ioc() {
    }

    static MyClassInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new MyClassImpl());
        return (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;
        private final Set<Method> annotationMethods;

        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
            this.annotationMethods = getOnlyAnnotationMethods(
                    Set.of(myClass.getClass().getDeclaredMethods()), Log.class);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotationMethods.contains(method)) {
                LogClass.log(method, args);
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }

        private Set<Method> getOnlyAnnotationMethods(Set<Method> annotationMethodSet, Class<? extends Annotation> annotationClass) {
            return annotationMethodSet.stream().filter(
                    method -> method.isAnnotationPresent(annotationClass)
            ).collect(Collectors.toSet());
        }
    }
}
