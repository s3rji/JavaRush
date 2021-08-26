package com.javarush.task.task18.task1816;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int count = 0;
        try (FileInputStream fileReader = new FileInputStream(args[0])) {
            while (fileReader.available() > 0) {
                int data = fileReader.read();
                if ((data >= 65 && data <= 90) || (data >= 97 && data <= 122))
                    count++;
            }

        }
        System.out.println(count);
    }
}
