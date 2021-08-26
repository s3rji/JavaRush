package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        List<StringBuilder> allUniqueSubstring = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!sb.toString().contains(String.valueOf(ch))) {
                sb.append(ch);
            }
            else {
                allUniqueSubstring.add(sb);
                sb = new StringBuilder();
                sb.append(ch);
            }
        }
        allUniqueSubstring.add(sb);

        Optional<StringBuilder> maxUniqueSubstring = allUniqueSubstring.stream().
                max(Comparator.comparingInt(string -> string.length()));

        return maxUniqueSubstring.get().length();
    }
}
