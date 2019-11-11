package com.OverSadBoy.samurairise.model;

import com.OverSadBoy.samurairise.model.database.Item;

import java.util.ArrayList;

public interface ModelContract {

    void addAlarm(Item item);
    void deleteAlarm(Item item);
    ArrayList<Item> getAlarms();
}
