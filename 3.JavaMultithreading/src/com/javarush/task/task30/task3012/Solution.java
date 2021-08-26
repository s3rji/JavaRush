package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1234);
    }

    public void createExpression(int number) {
        String res = "";
        int[] elements = {1, 3, 9, 27, 81, 243, 729, 2187};
        int i = 0;
        int original = number;

        while (number > 0) {
            switch (number % 3) {
                case 1:
                    res += " + " + elements[i];
                    break;
                case 2:
                    res += " - " + elements[i];
                    number++;
                    break;
            }
            i++;
            number /= 3;
        }

        System.out.println(original + " =" + res);
    }
}