package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        if (args[0].equals("-c")) {
            String name = args[1];
            Sex sex = args[2].equals("м") ? Sex.MALE : Sex.FEMALE;
            String[] birthDateParam = args[3].split("/");
            String birthDate = birthDateParam[1]+ "/" + birthDateParam[0] + "/" + birthDateParam[2];
            Date date = new Date(birthDate);
            Person person = sex == Sex.MALE ? Person.createMale(name, date) : Person.createFemale(name, date);
            allPeople.add(person);
            System.out.println(allPeople.size() - 1);
        }

        if (args[0].equals("-u")) {
            int index = Integer.parseInt(args[1]);
            String name = args[2];
            Sex sex = args[3].equals("м") ? Sex.MALE : Sex.FEMALE;
            String[] birthDateParam = args[4].split("/");
            String birthDate = birthDateParam[1]+ "/" + birthDateParam[0] + "/" + birthDateParam[2];
            Date date = new Date(birthDate);
            Person person = sex == Sex.MALE ? Person.createMale(name, date) : Person.createFemale(name, date);
            allPeople.set(index, person);
        }

        if (args[0].equals("-d")) {
            int index = Integer.parseInt(args[1]);
            allPeople.get(index).setName(null);
            allPeople.get(index).setSex(null);
            allPeople.get(index).setBirthDate(null);
        }

        if (args[0].equals("-i")) {
            int index = Integer.parseInt(args[1]);
            String name = allPeople.get(index).getName();
            String sex = allPeople.get(index).getSex() == Sex.MALE ? "м" : "ж";
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String date = formatter.format(allPeople.get(index).getBirthDate());
            System.out.println(name + " " + sex + " " + date);
        }
    }
}
