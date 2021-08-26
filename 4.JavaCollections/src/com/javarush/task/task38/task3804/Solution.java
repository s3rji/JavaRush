package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return FactoryExceptions.class;
    }

    public static void main(String[] args) {
        System.out.println(FactoryExceptions.getInstance(ApplicationExceptionMessage.SOCKET_IS_CLOSED));
        System.out.println(FactoryExceptions.getInstance(DatabaseExceptionMessage.NO_RESULT_DUE_TO_TIMEOUT));
        System.out.println(FactoryExceptions.getInstance(UserExceptionMessage.USER_DOES_NOT_HAVE_PERMISSIONS));
        System.out.println(FactoryExceptions.getInstance(null));
    }
}