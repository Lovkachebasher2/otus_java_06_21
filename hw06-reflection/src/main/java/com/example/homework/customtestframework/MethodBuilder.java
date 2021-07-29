package com.example.homework.customtestframework;

import java.lang.reflect.Method;
import java.util.List;

public class MethodBuilder {
    private List<Method> testMethodList;
    private Method beforeMethod;
    private Method afterMethod;

    private MethodBuilder() {

    }

    public List<Method> getTestMethodList() {
        return testMethodList;
    }

    public Method getBeforeMethod() {
        return beforeMethod;
    }

    public Method getAfterMethod() {
        return afterMethod;
    }

    public static Builder newBuilder() {
        return new MethodBuilder().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder withTestMethodList(List<Method> testMethodList) {
            MethodBuilder.this.testMethodList = testMethodList;
            return this;
        }

        public Builder withBeforeMethod(Method method) {
            MethodBuilder.this.beforeMethod = method;
            return this;
        }

        public Builder withAfterMethod(Method method) {
            MethodBuilder.this.afterMethod = method;
            return this;
        }

        public MethodBuilder build() {
            return MethodBuilder.this;
        }
    }
}