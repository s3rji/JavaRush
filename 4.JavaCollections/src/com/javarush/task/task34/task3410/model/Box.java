package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void draw(Graphics graphics) {
        int centerX = x - width/2;
        int centerY = y - height/2;

        graphics.setColor(Color.ORANGE);
        graphics.drawRect(centerX, centerY, width, height);
        graphics.drawLine(centerX, centerY, centerX + width, centerY + height);
        graphics.drawLine(centerX + width, centerY, centerX, centerY + height);
    }
}
