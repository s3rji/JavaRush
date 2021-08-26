package com.javarush.task.pro.task05.task0508;

import java.util.Scanner;

/* 
Удаляем одинаковые строки
*/

public class Solution {
    public static String[] strings;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        strings = new String[10];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = sc.nextLine();
        }

        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            for (int j = i + 1; j < strings.length; j++) {
                if (s == null)
                    break;
                if (s.equals(strings[j])) {
                    strings[i] = null;
                    strings[j] = null;
                }

            }
        }

        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + ", ");
        }
    }
}
