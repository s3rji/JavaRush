package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(number);

        byte[] buffer = new byte[text.getBytes().length];
        raf.read(buffer, 0, buffer.length);

        raf.seek(raf.length());

        if (text.equals(new String(buffer))) {
            raf.write("true".getBytes());
        } else {
            raf.write("false".getBytes());
        }
    }
}
