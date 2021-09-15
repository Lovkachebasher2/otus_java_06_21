package example.homework.proxy.aop;

public class ProxyDemo {
    public static void main(String[] args) {
        MyClassInterface myClass = Ioc.createMyClass();
        myClass.getAccess("admin");
        myClass.testMethod("text", 6);
    }
}
