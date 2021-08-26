package com.javarush.task.task38.task3804;

public class FactoryExceptions {
    public static Throwable getInstance(Enum type) {
        if (type == null) return new IllegalArgumentException();

        String nameWithSpace = type.toString().replace("_", " ").toLowerCase();
        String message = Character.toUpperCase(nameWithSpace.charAt(0)) + nameWithSpace.substring(1);

        switch (type.getClass().getSimpleName()) {
            case "ApplicationExceptionMessage":
                return new Exception(message);
            case "DatabaseExceptionMessage":
                return new RuntimeException(message);
            case "UserExceptionMessage":
                return new Error(message);
            default:
                return new IllegalArgumentException();
        }
    }
}
