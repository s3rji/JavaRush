package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    public static  StatisticManager getInstance() {
        if (instance == null)
            instance = new StatisticManager();
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public Map<String, Long> getVideoSelectedData() {
        Map<String, Long> videos = new TreeMap<>(Comparator.reverseOrder());
        List<EventDataRow> statistics = statisticStorage.get(EventType.SELECTED_VIDEOS);

        for (EventDataRow data : statistics) {
            VideoSelectedEventDataRow row = (VideoSelectedEventDataRow) data;

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String dateOfVideos = sdf.format(row.getDate());
            Long amountOfVideos = row.getAmount();

            if (videos.containsKey(dateOfVideos)) {
                videos.put(dateOfVideos, videos.get(dateOfVideos) + amountOfVideos);
            } else {
                videos.put(dateOfVideos, amountOfVideos);
            }
        }

        return videos;
    }

    public Map<String, TreeMap<String, Integer>> getCookedOrderData() {
        Map<String, TreeMap<String, Integer>> days = new TreeMap<>(Comparator.reverseOrder());
        List<EventDataRow> statistics = statisticStorage.get(EventType.COOKED_ORDER);

        for (EventDataRow data : statistics) {
            CookedOrderEventDataRow row = (CookedOrderEventDataRow) data;

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String dateOfCooking = sdf.format(row.getDate());
            String cookName = row.getCookName();
            int cookingTime = row.getTime();

            TreeMap<String, Integer> cooks = new TreeMap<>();
            if (days.containsKey(dateOfCooking)) {
                cooks = days.get(dateOfCooking);
                if (cooks.containsKey(cookName)) {
                    cooks.put(cookName, cooks.get(cookName) + cookingTime);
                } else {
                    cooks.put(cookName, cookingTime);
                }
            } else {
                cooks.put(cookName, cookingTime);
                days.put(dateOfCooking, cooks);
            }
        }

        return days;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                this.storage.put(type, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            EventType type = data.getType();
            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            this.storage.get(type).add(data);
        }

        private List<EventDataRow> get(EventType type) {
            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            return this.storage.get(type);
        }
    }
}
