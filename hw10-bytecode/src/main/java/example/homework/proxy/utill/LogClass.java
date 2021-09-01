package example.homework.proxy.utill;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogClass {

    public static void log(Method method, Object[] args) {
        new LogClass().logger(method, args);
    }

    private void logger(Method method, Object[] args) {
        System.out.println("executed method: " + method.getName() + ", with parameters: " + Arrays.toString(args));
    }
}
