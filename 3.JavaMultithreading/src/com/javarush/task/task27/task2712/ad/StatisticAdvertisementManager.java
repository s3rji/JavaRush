package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public List<Advertisement> activeVideos() {
        return advertisementStorage.list().stream().filter(Advertisement::isActive).collect(Collectors.toList());
    }

    public List<Advertisement> nonActiveVideos() {
        return advertisementStorage.list().stream().filter(advertisement -> !advertisement.isActive()).collect(Collectors.toList());
    }
}
