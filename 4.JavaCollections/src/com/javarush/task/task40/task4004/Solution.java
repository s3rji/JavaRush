package com.javarush.task.task40.task4004;

import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        int count = 0;
        Point a;
        Point b;
        for (int i = 0; i < polygon.size(); i++) {
            if (i == polygon.size() - 1) {
                a = polygon.get(i);
                b = polygon.get(0);
            } else {
                a = polygon.get(i);
                b = polygon.get(i + 1);
            }
            int check = check(a, b, point);
            if (check == -1) count++;
        }

        return count % 2 != 0;
    }

    public static int check(Point a, Point b, Point middle)
    {
        long ax = a.x - middle.x;
        long ay = a.y - middle.y;
        long bx = b.x - middle.x;
        long by = b.y - middle.y;
        int s = Long.signum(ax * by - ay * bx);
        if (s == 0 && (ay == 0 || by == 0) && ax * bx <= 0)
            return 0;
        if (ay < 0 ^ by < 0)
        {
            if (by < 0)
                return s;
            return -s;
        }
        return 1;
    }

}

