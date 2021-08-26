package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 's', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'}, // вернуть потом на same nlo
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        System.out.println(detectAllWords(crossword, "home", "same"));
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        int ver = crossword.length; // 5
        int hor = crossword[0].length; // 6

        for (String value : words) {
            char[] charFromValue = value.toCharArray();
            Point first = null;
            Point last = null;
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {

                    if (first == null) {
                        if (crossword[i][j] == value.charAt(0)) {
                            first = new Point(j, i, (char) crossword[i][j]);
                            // ищем направо
                            if (hor - j - value.length() >= 0) {
                                for (int k = 1; k < value.length(); k++) {

                                    if ((crossword[i][j + k] == value.charAt(k))) {
                                        last = new Point(j + k, i, (char) crossword[i][j + k]);
                                    } else {
                                        last = null;
                                        break;
                                    }

                                }
                            }
                            // ищем налево
                            if (last == null) {
                                if (j + 1 - value.length() >= 0) {
                                    for (int k = 1; k < value.length(); k++) {

                                        if ((crossword[i][j - k] == value.charAt(k))) {
                                            last = new Point(j - k, i, (char) crossword[i][j - k]);
                                        } else {
                                            last = null;
                                            break;
                                        }

                                    }
                                }
                            }
                            // ищем вниз
                            if (last == null) {
                                if (ver - i - value.length() >= 0) {
                                    for (int k = 1; k < value.length(); k++) {

                                        if ((crossword[i + k][j] == value.charAt(k))) {
                                            last = new Point(j, i + k, (char) crossword[i + k][j]);
                                        } else {
                                            last = null;
                                            break;
                                        }

                                    }
                                }
                            }
                            // ищем вверх
                            if (last == null) {
                                if (i + 1 - value.length() >= 0) {
                                    for (int k = 1; k < value.length(); k++) {

                                        if ((crossword[i - k][j] == value.charAt(k))) {
                                            last = new Point(j, i - k, (char) crossword[i - k][j]);
                                        } else {
                                            last = null;
                                            break;
                                        }

                                    }
                                }
                            }
                            // ищем по диагонали направо и вниз
                            if (last == null) {
                                if (ver - i - value.length() >= 0 && hor - j - value.length() >= 0) {
                                    for (int k = 1; k < value.length(); k++) {

                                        if ((crossword[i + k][j + k] == value.charAt(k))) {
                                            last = new Point(j + k, i + k, (char) crossword[i + k][j + k]);
                                        } else {
                                            last = null;
                                            break;
                                        }

                                    }
                                }
                            }
                            // ищем по диагонали налево и вниз
                            if (last == null) {
                                if (ver - i - value.length() >= 0 && j + 1 - value.length() >= 0) {
                                    for (int k = 1; k < value.length(); k++) {

                                        if ((crossword[i + k][j - k] == value.charAt(k))) {
                                            last = new Point(j - k, i + k, (char) crossword[i + k][j - k]);
                                        } else {
                                            last = null;
                                            break;
                                        }

                                    }
                                }
                            }
                            // ищем по диагонали налево и вверх
                            if (last == null) {
                                if (i + 1 - value.length() >= 0 && j + 1 - value.length() >= 0) {
                                    for (int k = 1; k < value.length(); k++) {

                                        if ((crossword[i - k][j - k] == value.charAt(k))) {
                                            last = new Point(j - k, i - k, (char) crossword[i - k][j - k]);
                                        } else {
                                            last = null;
                                            break;
                                        }

                                    }
                                }
                            }
                            // ищем по диагонали направо и вверх
                            if (last == null) {
                                if (i + 1 - value.length() >= 0 && hor - j - value.length() >= 0) {
                                    for (int k = 1; k < value.length(); k++) {

                                        if ((crossword[i - k][j + k] == value.charAt(k))) {
                                            last = new Point(j + k, i - k, (char) crossword[i - k][j + k]);
                                        } else {
                                            last = null;
                                            break;
                                        }

                                    }
                                }
                            }

                            if (last == null) first = null;
                        }
                    }

                }
            }

            if (first != null && last != null) {
                Word word = new Word(value);
                word.setStartPoint(first.x, first.y);
                word.setEndPoint(last.x, last.y);
                result.add(word);
            }
        }
        return result;
    }

    private static class Point {
        private final int x;
        private final int y;
        char value;

        private Point(int x, int y, char value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", value=" + value +
                    '}';
        }
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
