package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        Long total = 0L;
        for (Map.Entry<String, Long> pair : StatisticManager.getInstance().getVideoSelectedData().entrySet()) {
            ConsoleHelper.writeMessage(pair.getKey() + " - " + String.format(Locale.ENGLISH, "%.2f", pair.getValue()*1.0/100));
            total += pair.getValue();
        }
        ConsoleHelper.writeMessage("Total - " + String.format(Locale.ENGLISH, "%.2f", total*1.0/100));
    }

    public void printCookWorkloading() {
        for (Map.Entry<String, TreeMap<String, Integer>> days : StatisticManager.getInstance().getCookedOrderData().entrySet()) {
            ConsoleHelper.writeMessage(days.getKey());

            for (Map.Entry<String, Integer> cooks : days.getValue().entrySet()) {
                int cookedTime = (int) Math.ceil(cooks.getValue().doubleValue() / 60);
                ConsoleHelper.writeMessage(cooks.getKey() + " - " + cookedTime + " min");
            }
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> activeVideo = StatisticAdvertisementManager.getInstance().activeVideos();
        activeVideo.sort((ad1, ad2) -> ad1.getName().compareToIgnoreCase(ad2.getName()));
        activeVideo.forEach(advertisement -> ConsoleHelper.writeMessage(advertisement.getName() +
                                                                " - " +advertisement.getHits()));

    }

    public void printArchivedVideoSet() {
        List<Advertisement> nonActiveVideo = StatisticAdvertisementManager.getInstance().nonActiveVideos();
        nonActiveVideo.sort((ad1, ad2) -> ad1.getName().compareToIgnoreCase(ad2.getName()));
        nonActiveVideo.forEach(advertisement -> ConsoleHelper.writeMessage(advertisement.getName()));
    }
}
