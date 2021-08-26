package com.javarush.task.task19.task1909;

import java.io.*;
import java.util.ArrayList;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String srcFile;
        String dstFile;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            srcFile = reader.readLine();
            dstFile = reader.readLine();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(srcFile));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(dstFile))) {
            while (fileReader.ready()) {
                String nextLine = fileReader.readLine();
                String modifiedLine = nextLine.replace(".", "!");
                fileWriter.write(modifiedLine);
            }
        }

    }
}
