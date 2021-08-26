package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            String nextLine;
            String[] splittedLine;
            ArrayList<String> foundWords = new ArrayList<>();

            while (reader.ready()) {
                nextLine = reader.readLine();
                splittedLine = nextLine.split(" ");
                
                for (String word : splittedLine) {
                    if (word.length() > 6)
                        foundWords.add(word);
                }
            }

            for (int i = 0; i < foundWords.size(); i++) {
                if (i == foundWords.size() - 1)
                    writer.write(foundWords.get(i));
                else
                    writer.write(foundWords.get(i) + ",");
            }
        }

    }
}
