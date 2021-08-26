package com.javarush.task.task18.task1808;

import java.io.*;
import java.util.Scanner;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) {
        int halfSize;

        Scanner consoleReader = new Scanner(System.in);
        String fileFirst = consoleReader.nextLine();
        String fileSecond = consoleReader.nextLine();
        String fileThird = consoleReader.nextLine();

        try (FileInputStream fileFirstReader = new FileInputStream(fileFirst);
             FileOutputStream fileSecondWritter = new FileOutputStream(fileSecond);
             FileOutputStream fileThirdWritter = new FileOutputStream(fileThird)) {
            if (fileFirstReader.available() % 2 == 0)
                halfSize = fileFirstReader.available()/2;
            else
                halfSize = fileFirstReader.available()/2 + 1;

            byte[] buffer = new byte[halfSize];
            fileFirstReader.read(buffer);
            fileSecondWritter.write(buffer);

            int size = fileFirstReader.read(buffer);
            fileThirdWritter.write(buffer, 0, size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
