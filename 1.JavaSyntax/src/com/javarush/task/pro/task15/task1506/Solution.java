package com.javarush.task.pro.task15.task1506;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/* 
Пропускаем не всех
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
        List<String> list = Files.readAllLines(Path.of(scanner.nextLine()));
            for (String str : list) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) != ' ' && str.charAt(i) != '.' && str.charAt(i) != ',')
                        System.out.print(str.charAt(i));
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}

