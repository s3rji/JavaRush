package com.javarush.task.task18.task1820;

import java.io.*;
import java.util.Scanner;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        String firstFile = reader.nextLine();
        String secondFile = reader.nextLine();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(firstFile));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(secondFile))) {
            String[] lineFromFile;
            while (fileReader.ready()) {
                lineFromFile = fileReader.readLine().split(" ");
                for (int i = 0; i < lineFromFile.length; i++) {
                    double nextDouble = Double.parseDouble(lineFromFile[i]);
                    long nextLong = Math.round(nextDouble);
                    fileWriter.write(nextLong + " ");
                }
            }
        }
    }
}
