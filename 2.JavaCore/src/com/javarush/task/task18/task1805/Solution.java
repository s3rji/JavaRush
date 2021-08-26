package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] arrayofBytes = new int[256];

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            while (fileReader.available() > 0) {
                int data = fileReader.read();
                arrayofBytes[data] = 1;
            }
        }

        for (int i = 0; i < arrayofBytes.length; i++) {
            if (arrayofBytes[i] == 1) System.out.print(i + " ");
        }

    }
}
