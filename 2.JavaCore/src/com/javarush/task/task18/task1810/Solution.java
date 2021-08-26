package com.javarush.task.task18.task1810;

import java.io.*;
import java.util.Scanner;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException {
        Scanner reader = new Scanner(System.in);
        String fileSource = reader.nextLine();

        try {
            FileInputStream sourceReader = new FileInputStream(fileSource);
            while (!(sourceReader.available() < 1000)) {
                fileSource = reader.nextLine();
                sourceReader = new FileInputStream(fileSource);
            }
            sourceReader.close();
            throw new DownloadException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class DownloadException extends Exception {

    }
}
