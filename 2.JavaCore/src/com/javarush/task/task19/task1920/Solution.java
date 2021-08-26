package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;

        Map<String, Double> salary = new TreeMap<>();
        Double maxSalary = 0d;
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

        maxSalary = salary.values().stream().max(Double::compareTo).get();
        for (Map.Entry<String, Double> pair : salary.entrySet()) {
            if (pair.getValue().equals(maxSalary)) System.out.println(pair.getKey() + " ");
        }
    }
}
