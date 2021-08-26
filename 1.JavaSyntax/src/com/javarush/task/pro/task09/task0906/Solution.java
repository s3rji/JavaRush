package com.javarush.task.pro.task09.task0906;

import java.util.regex.Pattern;

/* 
Двоичный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = Integer.MAX_VALUE;
        System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toBinary(decimalNumber));
        String binaryNumber = "1111111111111111111111111111111";
        System.out.println("Двоичное число " + binaryNumber + " равно десятичному числу " + toDecimal(binaryNumber));
    }

    public static String toBinary(int decimalNumber) {
        String toBin = "";
        if (decimalNumber <= 0)
            return toBin;
        while (decimalNumber != 0){
            toBin = (decimalNumber % 2) + toBin;
            decimalNumber = decimalNumber / 2;
        }
        return toBin;
    }

    public static int toDecimal(String binaryNumber) {
        int toDec = 0;
        if (binaryNumber == null || binaryNumber.equals("") )
            return toDec;
        for (int i = 0; i < binaryNumber.length(); i++) {
            int index = binaryNumber.length() - 1 - i;
            int value = Character.getNumericValue(binaryNumber.charAt(index));
            toDec += value * Math.pow(2, i);
        }
        return toDec;
    }
}
