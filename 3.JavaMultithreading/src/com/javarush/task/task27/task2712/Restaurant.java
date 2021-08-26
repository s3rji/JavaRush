package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        DirectorTablet directorTablet = new DirectorTablet();

        Cook cookFirst = new Cook("Chief");
        Cook cookSecond = new Cook("Su-Chief");
        cookFirst.setQueue(ORDER_QUEUE);
        cookSecond.setQueue(ORDER_QUEUE);

        cookFirst.addObserver(new Waiter());
        cookSecond.addObserver(new Waiter());

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i + 1));
            tablets.get(i).setQueue(ORDER_QUEUE);
        }

        new Thread(cookFirst).start();
        new Thread(cookSecond).start();
        new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL)).start();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
