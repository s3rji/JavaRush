package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";

        List<String> charsCategory = new ArrayList<>();
        charsCategory.add(lower);
        charsCategory.add(upper);
        charsCategory.add(digits);

        Random rand = new Random(System.nanoTime());
        StringBuilder password = new StringBuilder(8);

        for (int i = 0; i < 3; i++) {
            String category = charsCategory.get(i);
            password.append(category.charAt(rand.nextInt(category.length())));
        }

        for (int i = 0; i < 5; i++) {
            String category = charsCategory.get(rand.nextInt(charsCategory.size()));
            password.append(category.charAt(rand.nextInt(category.length())));
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(password.toString().getBytes());

        return baos;
    }
}
