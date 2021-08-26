package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> parts = new ArrayList<>();
        String fileName = null;

        Scanner scanner = new Scanner(System.in);
        String part;
        while (!(part = scanner.nextLine()).equals("end")) {
            parts.add(part);
        }
        parts.sort(String::compareTo);

        if (parts.size() != 0) {
            int index = parts.get(0).lastIndexOf(".part");
            fileName = parts.get(0).substring(0, index);
        }

        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(fileName))) {
            for (String partOfFile : parts) {
                try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(partOfFile))) {
                    while (reader.available() > 0)
                        writer.write(reader.read());
                }
            }
        }

    }
}
