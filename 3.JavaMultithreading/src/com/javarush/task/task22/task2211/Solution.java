package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) return;

        try (FileInputStream fileReader = new FileInputStream(args[0]);
             FileOutputStream fileWriter = new FileOutputStream(args[1])) {
            byte[] buffer = new byte[1024];
            while (fileReader.available() > 0) {
                fileReader.read(buffer);
                String converter = new String(buffer, "Windows-1251");
                buffer = converter.getBytes("UTF-8");
                fileWriter.write(buffer);
            }
        }
    }
}
