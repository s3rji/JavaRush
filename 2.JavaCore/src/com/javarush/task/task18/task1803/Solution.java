package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> counterBytes = new HashMap<>();
        int maxCount = 0;

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            while(fileReader.available() > 0) {
                int data = fileReader.read();
                if (counterBytes.containsKey(data))
                    counterBytes.put(data, counterBytes.get(data) + 1);
                else
                    counterBytes.put(data, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, Integer> pair : counterBytes.entrySet()) {
            if (pair.getValue() > maxCount) {
                maxCount = pair.getValue();
            }
        }

        for (Map.Entry<Integer, Integer> pair : counterBytes.entrySet()) {
            if (pair.getValue() == maxCount) {
                System.out.print(pair.getKey() + " ");
            }
        }

    }
}
