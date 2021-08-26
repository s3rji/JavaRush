package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName;

        while (!(fileName = scanner.nextLine()).equals("exit")) {
            new ReadThread(fileName).run();
        }

        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            int[] characters = new int[256];
            int maxCount = 0;

            try (FileInputStream reader = new FileInputStream(fileName)) {
                while (reader.available() > 0) {
                    characters[reader.read()]++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < characters.length; i++) {
                if (characters[i] > maxCount) maxCount = characters[i];
            }

            for (int i = 0; i < characters.length; i++) {
                if (characters[i] == maxCount)
                    resultMap.put(fileName, i);
            }
        }
    }
}
