package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            String nextLine;
            StringBuilder reversLine = new StringBuilder();

            while (fileReader.ready()) {
                nextLine = fileReader.readLine();
                reversLine.append(nextLine).reverse();
                System.out.println(reversLine);
                reversLine.delete(0, reversLine.length());
            }
        }
    }
}
