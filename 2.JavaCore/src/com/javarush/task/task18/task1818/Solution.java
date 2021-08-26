package com.javarush.task.task18.task1818;

import java.io.*;
import java.util.Scanner;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        String firstFile = reader.nextLine();
        String secondFile = reader.nextLine();
        String thirdFile = reader.nextLine();

        try (FileOutputStream writterOfFirstFile = new FileOutputStream(firstFile);
             FileInputStream readerOfSecondFile = new FileInputStream(secondFile)) {
            while (readerOfSecondFile.available() > 0) {
                int data = readerOfSecondFile.read();
                writterOfFirstFile.write(data);
            }
        }

        try (FileOutputStream writterOfFirstFile = new FileOutputStream(firstFile, true);
             FileInputStream readerOfThirdFile = new FileInputStream(thirdFile)) {
            while (readerOfThirdFile.available() > 0) {
                int data = readerOfThirdFile.read();
                writterOfFirstFile.write(data);
            }
        }

    }
}
