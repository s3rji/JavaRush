package com.javarush.task.task19.task1918;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName;
        StringBuilder contentFile = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                contentFile.append(fileReader.readLine());
            }
        }
        String stringContent = contentFile.toString().replaceAll("[\\r\\n]+", "");

        String tag = args[0];
        String openedTag = "<" + tag;
        String closedTag = "</" + tag;
        int openedTagIndex = stringContent.indexOf(openedTag);
        int closedTagIndex = stringContent.indexOf(closedTag);
        int openedTagCount = 0;
        int closedTagCount = 0;
        ArrayList<Integer> openedTagsIndexes = new ArrayList<>();
        ArrayList<Integer> closedTagsIndexes = new ArrayList<>();
        ArrayList<Integer> tempClosedTagsIndexes = new ArrayList<>();

        while (openedTagIndex != -1 || closedTagIndex != -1) {
            if (openedTagIndex != -1 && openedTagIndex < closedTagIndex) {
                openedTagsIndexes.add(openedTagIndex);
                openedTagCount++;
                openedTagIndex = stringContent.indexOf(openedTag, openedTagIndex + 1);
            } else if (closedTagCount != -1 && (closedTagIndex < openedTagIndex || openedTagIndex == -1)) {
                tempClosedTagsIndexes.add(closedTagIndex + tag.length() + 3);
                closedTagCount++;
                closedTagIndex = stringContent.indexOf(closedTag, closedTagIndex + 1);
            }

            if (closedTagCount == openedTagCount) {
                for (int i = tempClosedTagsIndexes.size() - 1; i >= 0; i--) {
                    closedTagsIndexes.add(tempClosedTagsIndexes.get(i));
                }
                tempClosedTagsIndexes.clear();
            }
        }

        for (int i = 0; i < openedTagsIndexes.size(); i++) {
            System.out.println(stringContent.substring(openedTagsIndexes.get(i), closedTagsIndexes.get(i)));
        }
    }
}
