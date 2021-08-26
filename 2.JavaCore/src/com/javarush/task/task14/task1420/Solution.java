package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberFirst = Integer.parseInt(reader.readLine());
        int numberSecond = Integer.parseInt(reader.readLine());
        if (numberFirst < 1 || numberSecond < 1) {
            throw new IllegalArgumentException();
        }
        System.out.println(nod(numberFirst, numberSecond));
        System.out.println(96 % 140);
    }

    public static int nod(int numberFirst, int numberSecond) {
        int nod;
        int max = Math.max(numberFirst, numberSecond);
        int min = Math.min(numberFirst, numberSecond);
        if (max % min == 0)
            nod = min;
        else {
            nod = nod(min, max % min);
        }
        return nod;
    }

}
