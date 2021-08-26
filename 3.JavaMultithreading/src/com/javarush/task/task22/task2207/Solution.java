package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready())
                fileContent.append(reader.readLine()).append(" ");
        }

        List<String> splitFileContent = new ArrayList<>(Arrays.asList(fileContent.toString().trim().split(" ")));

        for (int i = 0; i < splitFileContent.size(); i++) {
            for (int j = i + 1; j < splitFileContent.size(); j++) {
                if (splitFileContent.get(i).equals(new StringBuilder(splitFileContent.get(j)).reverse().toString())) {
                    Pair pair = new Pair();
                    pair.first = splitFileContent.get(i);
                    pair.second = splitFileContent.get(j);
                    result.add(pair);

                    splitFileContent.remove(j);
                    splitFileContent.remove(i);

                    i--;
                    break;
                }
            }
        }

        System.out.println(fileContent);
        System.out.println(result);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
