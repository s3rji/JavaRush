package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        if (s.startsWith("0")) {
            char[] chars = s.substring(1).toCharArray();
            if (chars[0] == 'x') {
                String result = String.valueOf(chars, 1, chars.length - 1);
                return String.valueOf(Integer.parseInt(result, 16));
            } else if (chars[0] == 'b') {
                String result = String.valueOf(chars, 1, chars.length - 1);
                return String.valueOf(Integer.parseInt(result, 2));
            } else {
                String result = String.valueOf(chars);
                return String.valueOf(Integer.parseInt(result, 8));
            }
        } else
            return s;
    }
}
