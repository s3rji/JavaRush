package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int max = -128;
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        FileInputStream input = new FileInputStream(fileName);
        while(input.available() > 0) {
            int data = input.read();
            if (data > max) {
                max = data;
            }
        }
        input.close();

        System.out.println(max);
    }
}
