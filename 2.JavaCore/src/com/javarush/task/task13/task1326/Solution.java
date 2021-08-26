package com.javarush.task.task13.task1326;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        FileInputStream input = new FileInputStream(fileName);
        Scanner reader = new Scanner(input);

        while (reader.hasNextInt()) {
            numbers.add(reader.nextInt());
        }

        numbers.stream().filter(n -> n % 2 == 0).sorted((n1, n2) -> n1 - n2).forEach(System.out::println);
        reader.close();
    }
}
