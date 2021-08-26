package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String firstFile;
        String secondFile;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            firstFile = reader.readLine();
            secondFile = reader.readLine();
        }

        try (FileReader fileReader = new FileReader(firstFile);
             FileWriter fileWriter = new FileWriter(secondFile)) {
            while (fileReader.ready()) {
                fileReader.read();
                fileWriter.write(fileReader.read());
            }
        }
    }
}
