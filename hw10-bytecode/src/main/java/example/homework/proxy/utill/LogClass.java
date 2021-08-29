package example.homework.proxy.utill;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogClass {

    public static void log(Method method, Object[] args) {
        new LogClass().logger(method, args);
    }

    private void logger(Method method, Object[] args) {
        StringBuilder stringBuilder = new StringBuilder("executed method: ")
                .append(method.getName())
                .append(", ")
                .append("with parameters: ")
                .append(Arrays.toString(args));
        System.out.println(stringBuilder);
    }
}
