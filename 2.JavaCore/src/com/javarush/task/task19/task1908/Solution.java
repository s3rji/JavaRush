package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String firstFile;
        String secondFile;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            firstFile = reader.readLine();
            secondFile = reader.readLine();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(firstFile));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(secondFile))) {
            while (fileReader.ready()) {
                String[] split = fileReader.readLine().split(" ");
                for (String s : split) {
                    if (s.matches("\\d+"))
                        fileWriter.write(s + " ");
                }
            }
        }
    }
}
