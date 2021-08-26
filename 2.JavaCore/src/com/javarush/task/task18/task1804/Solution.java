package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] arrayOfBytes = new int[256];
        int minCount = Integer.MAX_VALUE;
        Arrays.fill(arrayOfBytes, minCount);
        ArrayList<Integer> result = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            while (fileReader.available() > 0) {
                int data = fileReader.read();
                arrayOfBytes[data] += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < arrayOfBytes.length; i++) {
            if (arrayOfBytes[i] < minCount) minCount = arrayOfBytes[i];
        }

        for (int i = 0; i < arrayOfBytes.length; i++) {
            if (arrayOfBytes[i] == minCount) result.add(i);
        }

        for (Integer value : result) {
            System.out.print(value + " ");
        }
    }
}
