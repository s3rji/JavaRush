package com.javarush.task.task18.task1821;

import org.w3c.dom.ls.LSOutput;

import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<Character, Integer> chars = new TreeMap<>();

        try (FileReader fileReader = new FileReader(args[0])) {
            while (fileReader.ready()) {
                Character nextChar = (char) fileReader.read();
                if (!chars.containsKey(nextChar))
                    chars.put(nextChar, 1);
                else
                    chars.put(nextChar, chars.get(nextChar) + 1);
            }
        }

        chars.forEach((character, integer) -> System.out.println(character + " " + integer));
    }
}
