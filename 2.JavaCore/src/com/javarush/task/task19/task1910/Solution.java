package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;

/* 
Пунктуация
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
                String replacedLine = nextLine.replaceAll("\\p{P}", "")
                                                .replaceAll("\\n", "");
                fileWriter.write(replacedLine);
            }
        }
    }
}
