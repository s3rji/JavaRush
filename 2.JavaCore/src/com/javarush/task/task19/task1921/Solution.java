package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) return;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(args[0]))) {
            String nextLine;
            String[] splitedLine;
            Person person;

            while (fileReader.ready()) {
                StringBuilder name = new StringBuilder();

                nextLine = fileReader.readLine();
                splitedLine = nextLine.split(" ");

                for (int i = 0; i < splitedLine.length - 3; i++) {
                    name.append(splitedLine[i]).append(" ");
                }

                String birthDate = splitedLine[splitedLine.length - 2] + "/" + splitedLine[splitedLine.length - 3] +
                                    "/" + splitedLine[splitedLine.length-1];

                person = new Person(name.toString().trim(), new Date(birthDate));
                PEOPLE.add(person);
            }
        }
    }
}
