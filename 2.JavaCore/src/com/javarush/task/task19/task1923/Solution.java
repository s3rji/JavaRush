package com.javarush.task.task19.task1923;

import java.io.*;
import java.util.Arrays;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            String nextLine;
            String[] splittedLine;

            while (reader.ready()) {
                nextLine = reader.readLine();
                splittedLine = nextLine.split(" ");

                for (String word : splittedLine) {
                    if (word.matches(".*[0-9].*"))
                        writer.write(word + " ");
                }
            }
        }
    }
}
