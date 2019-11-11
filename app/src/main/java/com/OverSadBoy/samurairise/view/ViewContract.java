package com.OverSadBoy.samurairise.view;

import com.OverSadBoy.samurairise.model.database.Item;

import java.util.ArrayList;

public interface ViewContract {
    void addAlarm(Item item);

    void deleteAlarm(Item item);

    void updateStatus(Item item, boolean status);

    ArrayList<Item> loadData();

    void startActivityAdd();
}
