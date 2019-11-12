package com.OverSadBoy.samurairise.model;

import com.OverSadBoy.samurairise.model.database.Item;

import java.util.ArrayList;

public interface ModelContract {

    void addAlarm(Item item);
    void deleteAlarm(int id);
    ArrayList<Item> getAlarms();
}
