package com.javarush.task.task18.task1824;

import java.io.*;
import java.util.Scanner;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String fileName = scanner.nextLine();
            try (FileInputStream reader = new FileInputStream(fileName)) {

            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
