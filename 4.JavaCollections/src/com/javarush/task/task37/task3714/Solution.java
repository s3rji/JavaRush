package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isSubtract(s, i))
                sum -= map.get(s.charAt(i));
            else
                sum += map.get(s.charAt(i));
        }

        return sum;
    }

    public static boolean isSubtract (String s, int index) {
        if (index == s.length() - 1) return false;

        switch (s.charAt(index)) {
            case 'I':
                if (s.charAt(index + 1) == 'V' || (s.charAt(index + 1) == 'X'))
                    return true;
            case 'X':
                if (s.charAt(index + 1) == 'L' || (s.charAt(index + 1) == 'C'))
                    return true;
            case 'C':
                if (s.charAt(index + 1) == 'D' || (s.charAt(index + 1) == 'M'))
                    return true;
            default:
                return false;
        }
    }
}
