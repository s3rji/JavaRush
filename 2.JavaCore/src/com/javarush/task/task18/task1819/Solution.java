package com.javarush.task.task18.task1819;

import java.io.*;
import java.util.Scanner;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String firstFile = scanner.nextLine();
        String secondFile = scanner.nextLine();

        byte[] bytesFromFile;
        try (FileInputStream reader = new FileInputStream(firstFile)) {
            bytesFromFile = new byte[reader.available()];
            int index = 0;
            while (reader.available() > 0) {
                bytesFromFile[index] = (byte) reader.read();
                index++;
            }
        }

        try (FileOutputStream writer = new FileOutputStream(firstFile);
             FileInputStream reader = new FileInputStream(secondFile)) {
            while (reader.available() > 0) {
                writer.write(reader.read());
            }
            writer.write(bytesFromFile);
        }


    }
}
