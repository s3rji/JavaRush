package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Lutik", 3, 0));
        horses.add(new Horse("Mustang", 3, 0));
        horses.add(new Horse("Lucky", 3, 0));
        
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (Horse horse : horses) {
            winner = Double.compare(horse.getDistance(), winner.getDistance()) > 0 ? horse : winner;
        }
        return winner;
    }

    public void printWinner() {
        System.out.printf("Winner is %s!%n", getWinner().getName());
    }
}
