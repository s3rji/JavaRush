package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("name", "Ivanov");
        data.put("country", "Ukraine");
        data.put("city", "Kiev");
        data.put("age", null);

        System.out.println(getQuery(data));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (pair.getValue() != null) {
                if (result.length() != 0)
                    result.append(" and ");
                result.append(pair.getKey()).append(" = '").append(pair.getValue()).append("'");
            }
        }

        return result.toString();
    }
}
