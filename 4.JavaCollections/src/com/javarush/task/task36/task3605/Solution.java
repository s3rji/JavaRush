package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) return;

        try (InputStream in = Files.newInputStream(Paths.get(args[0]))) {
            TreeSet<Character> treeSet = new TreeSet<>();

            int i;
            char ch;
            while ((i = in.read()) != -1) {
                ch = (char) i;
                if (Character.isAlphabetic(ch)) treeSet.add(Character.toLowerCase(ch));
            }

            int k = 0;
            for (Character c : treeSet) {
                if (k < 5) System.out.print(c);
                k++;
            }
        }
    }
}
