package example.homework.proxy.aop;

import example.homework.proxy.utill.Log;

public interface MyClassInterface {

    void secureAccess(String param);

    @Log
    void getAccess(String role);

    @Log
    void testMethod(String text, int i);
}
