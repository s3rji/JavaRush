package com.javarush.task.task18.task1817;

import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        long spaceCounter = 0;
        long allCharacterCounter = 0;
        double result;

        try (FileReader fileReader = new FileReader(args[0])) {
            while (fileReader.ready()) {
                char next = (char) fileReader.read();
                allCharacterCounter++;
                if (next == ' ')
                    spaceCounter++;
            }
        }

        result = ((double) spaceCounter)/allCharacterCounter*100;
        System.out.println(Math.round(result*100)/100.00);

    }
}
