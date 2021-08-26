package com.javarush.task.task18.task1807;

import java.io.*;
import java.util.Scanner;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) {
        int counter = 0;
        Scanner reader = new Scanner(System.in);

        try (FileInputStream fileReader = new FileInputStream(reader.nextLine())) {
            while (fileReader.available() > 0) {
                int data = fileReader.read();
                if (data == ((int) ','))
                    counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }
}
