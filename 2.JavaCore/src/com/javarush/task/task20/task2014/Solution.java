package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) {

        Solution savedObject = new Solution(30);

        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\S3rg\\Desktop\\Занятие Java\\Для задач\\test2.txt");
             FileInputStream inputStream = new FileInputStream("C:\\Users\\S3rg\\Desktop\\Занятие Java\\Для задач\\test2.txt")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            objectOutputStream.writeObject(savedObject);

            Solution loadedObject = new Solution(25);
            loadedObject = (Solution) objectInputStream.readObject();

            System.out.println(savedObject.string.equals(loadedObject.string));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
