package com.javarush.task.pro.task15.task1504;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
        InputStream inputStream = Files.newInputStream(Path.of(scanner.nextLine()));
        OutputStream outputStream = Files.newOutputStream(Path.of(scanner.nextLine()))) {
            byte[] buffer = new byte[2];
            while (inputStream.available() > 0) {
                int read = inputStream.read(buffer);
                if (read > 1) {
                    outputStream.write(buffer[1]);
                }
                outputStream.write(buffer[0]);
            }

        } catch (IOException e) {
            System.out.println("Something went wrong : " + e);
        }

    }
}

