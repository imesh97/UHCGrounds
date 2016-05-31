package xyz.imdafatboss.uhcgrounds.utils;

public class Debugger {

    public static void debug(String message) {
        System.out.println("[DEBUGGER: " + getStackTraceElement().getFileName() + " " + getStackTraceElement().getLineNumber() + "] " + message);
    }

    private static StackTraceElement getStackTraceElement() {
        return Thread.currentThread().getStackTrace()[3];
    }

}