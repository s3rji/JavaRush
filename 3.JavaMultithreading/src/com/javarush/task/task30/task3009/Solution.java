package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();

        try {
            BigInteger value = new BigInteger(number);
            for (int i = 2; i <= 36; i++) {
                String tmp = value.toString(i);
                String reverse = new StringBuilder(tmp).reverse().toString();
                if (tmp.equals(reverse))
                    result.add(i);
            }
        } catch (NumberFormatException ignor) {

        }

        return result;
    }
 }