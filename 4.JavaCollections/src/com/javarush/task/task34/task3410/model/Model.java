package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader;

    public Model() {
        try {
            levelLoader = new LevelLoader(Paths.get(getClass().getResource("../res/levels.txt").toURI()));
        } catch (URISyntaxException e) {
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)) return;

        if (checkBoxCollisionAndMoveIfAvailable(direction)) return;

        switch (direction) {
            case LEFT:
                player.move( - FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, - FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0,  FIELD_CELL_SIZE);
                break;
        }

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();

        for (Box currentBox : boxes) {
            boolean isCollision = player.isCollision(currentBox, direction);

            if (isCollision) {

                if (!checkWallCollision(currentBox, direction)) {
                    for (Box otherBox : gameObjects.getBoxes()) {
                        if (currentBox.isCollision(otherBox, direction)) {
                            return true;
                        }
                    }

                    switch (direction) {
                        case LEFT:
                            currentBox.move( - FIELD_CELL_SIZE, 0);
                            break;
                        case RIGHT:
                            currentBox.move(FIELD_CELL_SIZE, 0);
                            break;
                        case UP:
                            currentBox.move(0, - FIELD_CELL_SIZE);
                            break;
                        case DOWN:
                            currentBox.move(0,  FIELD_CELL_SIZE);
                            break;
                    }
                } else {
                    return true;
                }


            }
        }

        return false;
    }

    public void checkCompletion() {
        Set<Box> boxes = gameObjects.getBoxes();
        Set<Home> homes = gameObjects.getHomes();

        int count = 0;
        for (Box box : boxes) {
            for (Home home : homes) {
                if (box.getX() == home.getX() && box.getY() == home.getY())
                    count++;
            }
        }

        if (count == boxes.size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
