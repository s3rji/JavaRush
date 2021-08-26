package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private final String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);

        CookedOrderEventDataRow cookedEvent = new CookedOrderEventDataRow(order.getTablet().toString(), name,
                order.getTotalCookingTime() * 60, order.getDishes());
        StatisticManager.getInstance().register(cookedEvent);

        try {
            Thread.sleep(order.getTotalCookingTime()*10L);
        } catch (InterruptedException e) {

        }

        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!queue.isEmpty()) {
                    if (!isBusy())
                        startCookingOrder(queue.take());
                }
                Thread.sleep(10);
            }

        } catch (InterruptedException e) {

        }
    }
}
