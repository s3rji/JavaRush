package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
        Integer[] test = {13, 8, 15, 5, 17};
        sort(test);
    }

    public static Integer[] sort(Integer[] array) {
        Integer[] copy = array.clone();
        Arrays.sort(copy);

        double median;

        if (copy.length % 2 != 0) {
            median = copy[copy.length / 2];
        } else
            median = (copy[(copy.length - 1) / 2] + copy[(copy.length + 1) / 2]) / 2d;

        /*Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double v1 = o1.intValue() - median;
                double v2 = o2.intValue() - median;
                return (int) ((v1 * v1 - v2 * v2) * 100);
            }
        });*/

        Arrays.sort(array, Comparator.comparingDouble(o -> Math.abs(o - median)));

        System.out.println(Arrays.toString(array));
        return array;
    }
}
