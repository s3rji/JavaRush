package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> dataOfFirstFile = new ArrayList<>();
        ArrayList<String> dataOfSecondFile = new ArrayList<>();
        String firstFile;
        String secondFile;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            firstFile = reader.readLine();
            secondFile = reader.readLine();
        }

        try (BufferedReader firstReader = new BufferedReader(new FileReader(firstFile));
             BufferedReader secondReader = new BufferedReader(new FileReader(secondFile))) {
                while (firstReader.ready())
                    dataOfFirstFile.add(firstReader.readLine());
                while (secondReader.ready())
                    dataOfSecondFile.add(secondReader.readLine());
        }

        for (int i = 0, j = 0; i < dataOfFirstFile.size() || j < dataOfSecondFile.size(); ) {
            if (i == dataOfFirstFile.size()) {
                lines.add(new LineItem(Type.ADDED, dataOfSecondFile.get(j)));
                j++;
                continue;
            }

            if (j == dataOfSecondFile.size()) {
                lines.add(new LineItem(Type.REMOVED, dataOfFirstFile.get(i)));
                i++;
                continue;
            }

            if (dataOfFirstFile.get(i).equals(dataOfSecondFile.get(j))) {
                lines.add(new LineItem(Type.SAME, dataOfFirstFile.get(i)));
                i++;
                j++;
            } else if ((i + 1) == dataOfFirstFile.size() || dataOfFirstFile.get(i + 1).equals(dataOfSecondFile.get(j))) {
                lines.add(new LineItem(Type.REMOVED, dataOfFirstFile.get(i)));
                i++;
            } else if ((j + 1) ==dataOfSecondFile.size() || dataOfFirstFile.get(i).equals(dataOfSecondFile.get(j + 1))) {
                lines.add(new LineItem(Type.ADDED, dataOfSecondFile.get(j)));
                j++;
            }
        }

        System.out.println(lines);
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return "LineItem{" +
                    "type=" + type +
                    ", line='" + line + '\'' +
                    '}';
        }
    }
}
