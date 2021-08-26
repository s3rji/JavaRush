package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName;
        String nextLine;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        do {
            nextLine = reader.readLine();
            writer.write(nextLine);
            writer.newLine();
        } while (!nextLine.equals("exit"));

        writer.close();
    }
}
