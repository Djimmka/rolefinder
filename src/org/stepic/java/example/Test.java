package org.stepic.java.example;

public class Test {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] sTe = new Exception().getStackTrace();
               // Thread.currentThread().getStackTrace();
        String str = "";
        int i = 0;
        for (StackTraceElement element : sTe) {
            if (i > 1) {
                str = element.getMethodName() + "." + str;
            } else {
                if (i == 1) {
                    str = element.getMethodName();
                }
            }
            i++;

            //System.out.println(element.getMethodName());
            //System.out.println(i + " " + str);
        }
        if (str.equals("main")) {
            return null;
        }
        return str;
    }
}