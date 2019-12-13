package com.OverSadBoy.samurairise.model;

import com.OverSadBoy.samurairise.model.database.Item;

import java.util.List;

public interface ModelContract {

    void addAlarm(Item item);
    void deleteAlarm(int id);
    List<Item> getAlarms();
}
