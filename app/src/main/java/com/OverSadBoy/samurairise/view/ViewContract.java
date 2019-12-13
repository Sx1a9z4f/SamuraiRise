package com.OverSadBoy.samurairise.view;

import com.OverSadBoy.samurairise.model.database.Item;

import java.util.List;

public interface ViewContract {
    void addAlarm(Item item);

    void deleteAlarm(Item item);

    List<Item> loadData();

    void startActivityAdd();
}
