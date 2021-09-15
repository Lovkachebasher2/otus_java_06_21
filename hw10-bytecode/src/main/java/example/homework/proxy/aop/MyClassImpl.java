package example.homework.proxy.aop;

public class MyClassImpl implements MyClassInterface {
    @Override
    public void secureAccess(String param) {
        System.out.println("secureAccess, param:" + param);
    }

    @Override
    public void getAccess(String role) {
        System.out.println("access " + role + " got it");
    }

    @Override
    public void testMethod(String text, int i) {
        System.out.println();
    }


    @Override
    public String toString() {
        return "MyClassImpl{}";
    }


}
