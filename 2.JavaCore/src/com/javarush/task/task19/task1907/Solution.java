package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int count = 0;
        String file;
        StringBuilder allWords = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file = reader.readLine();
        }

        try (FileReader fileReader = new FileReader(file)) {
            while (fileReader.ready()) {
                allWords.append((char) fileReader.read());

            }
        }

        String replacedString = allWords.toString().replaceAll("\\p{P}", " ").replaceAll("\\s", " ");

        for (String word : replacedString.split(" ")) {
            if (word.equals("world"))
                count++;
        }
        System.out.println(count);

    }
}
