package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) {
        String fileSource = args[1];
        String fileDest = args[2];
        int password = 31;

        try (FileInputStream sourceReader = new FileInputStream(fileSource);
             FileOutputStream destReader = new FileOutputStream(fileDest)) {
            while (sourceReader.available() > 0) {
                int data = sourceReader.read();
                int encrypt = data ^ password;
                destReader.write(encrypt);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
