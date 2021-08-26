package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder("");
        String paramObj = null;

        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();

        int index = url.indexOf("?");
        String parameters = url.substring(index+1);
        String[] allParameters = parameters.split("&");

        for (String s : allParameters) {
            if (s.contains("=")){
                int ind = s.indexOf("=");
                result.append(s, 0, ind).append(" ");
            } else
                result.append(s).append(" ");
        }
        for (String s : allParameters) {
            if (s.contains("obj"))
                paramObj = s;
        }

        System.out.println(result);

        if (paramObj != null) {
            int indexObj = paramObj.indexOf("=");
            try {
                alert(Double.parseDouble(paramObj.substring(indexObj + 1)));
            } catch (Exception e) {
                alert(paramObj.substring(indexObj + 1));
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
