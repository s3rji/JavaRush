package com.javarush.task.pro.task05.task0505;

import java.util.Scanner;

/* 
Reverse
*/

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        if (n % 2 == 0) {
            for (int i = 0; i < n; i++) {
                System.out.println(numbers[n-1-i]);
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                System.out.println(numbers[i]);
            }
        }
    }

}
