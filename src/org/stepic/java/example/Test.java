package org.stepic.java.example;

public class Test {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
//        aMet();
//        bMet();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

//    private static void aMet(){
//        anotherMethod();
//        System.out.println(getCallerClassAndMethodName());
//    }

//    private static void bMet(){
//        aMet();
//        System.out.println(getCallerClassAndMethodName());
//    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] sTe = new Exception().getStackTrace();
        String str = null;
        int i = 0;
        for (StackTraceElement element : sTe) {
            if ((i > 1)) {
                return str = element.getClassName() + "#" + element.getMethodName();
            }
            i++;
        }
        if (i < 3) {
            return null;
        }
        return str;
    }
}