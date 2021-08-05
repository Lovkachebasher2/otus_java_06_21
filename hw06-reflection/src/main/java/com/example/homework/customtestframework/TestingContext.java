package com.example.homework.customtestframework;

import java.lang.reflect.Method;
import java.util.List;

public class TestingContext {
    private List<Method> testMethodList;
    private Method beforeMethod;
    private Method afterMethod;

    private TestingContext() {

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
        return new TestingContext().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder withTestMethodList(List<Method> testMethodList) {
            TestingContext.this.testMethodList = testMethodList;
            return this;
        }

        public Builder withBeforeMethod(Method method) {
            TestingContext.this.beforeMethod = method;
            return this;
        }

        public Builder withAfterMethod(Method method) {
            TestingContext.this.afterMethod = method;
            return this;
        }

        public TestingContext build() {
            return TestingContext.this;
        }
    }
}