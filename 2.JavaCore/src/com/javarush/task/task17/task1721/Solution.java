package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        String fileFirst;
        String fileSecond;

        Scanner scanner = new Scanner(System.in);
        fileFirst = scanner.nextLine();
        fileSecond = scanner.nextLine();

        try (BufferedReader readerFirst = new BufferedReader(new FileReader(fileFirst));
        BufferedReader readerSecond = new BufferedReader(new FileReader(fileSecond))) {
            while(readerFirst.ready()) {
                allLines.add(readerFirst.readLine());
            }

            while(readerSecond.ready()) {
                forRemoveLines.add(readerSecond.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }

        System.out.println(allLines);
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
