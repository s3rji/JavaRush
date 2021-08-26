package com.javarush.task.pro.task15.task1523;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/* 
Получение информации по API
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://httpbin.org/post");
        URLConnection connect = url.openConnection();
        connect.setDoOutput(true);
        try (PrintStream writer = new PrintStream(connect.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()))) {
            writer.println("Hello");
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }

        }

    }
}

