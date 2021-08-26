package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);
        String fileSource = reader.nextLine();
        String fileDest = reader.nextLine();

        try (FileInputStream sourceReader = new FileInputStream(fileSource);
             FileOutputStream destWritter = new FileOutputStream(fileDest)) {
            byte[] buffer = new byte[sourceReader.available()];
            sourceReader.read(buffer);
            for (int i = buffer.length - 1; i >= 0; i--) {
                destWritter.write(buffer[i]);
            }
        }
    }
}
