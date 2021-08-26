package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int minByte = 127;
        Scanner reader = new Scanner(System.in);
        String fileName = reader.nextLine();

        try (FileInputStream input = new FileInputStream(fileName)) {
            while (input.available() > 0) {
                int nextByte = input.read();
                if (nextByte < minByte) minByte = nextByte;
            }
        }

        System.out.println(minByte);
    }
}
