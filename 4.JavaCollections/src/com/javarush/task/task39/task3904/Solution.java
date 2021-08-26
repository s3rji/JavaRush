package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/

public class Solution {
    private static int n = 2;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;

        long[] sum = new long[n + 1];
        sum[0] = 1;
        sum[1] = 1;
        sum[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            sum[i] = sum[i - 1] + sum[i - 2] + sum[i - 3];
        }

        return sum[n] ;
    }
}

