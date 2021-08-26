package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;

        Map<String, Double> salary = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                String nextLine = reader.readLine();
                String[] split  = nextLine.split(" ");
                if (salary.size() > 0 && salary.containsKey(split[0]))
                    salary.put(split[0], salary.get(split[0]) + Double.parseDouble(split[1]));
                else
                    salary.put(split[0], Double.parseDouble(split[1]));
            }
        }

        salary.forEach((s, d) -> System.out.print(s + " " + d + "\n"));
    }
}
